<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<%@include file="../common/css.jsp"%>

<!-- The styles -->
<style type="text/css">
</style>

</head>

<body>
	<%@include file="../common/header.jsp"%>

	<%@include file="../common/nav2.jsp"%>
	<div class="col-lg-10 index-iframe" >
	<div class="docInput-top">
		<ol class="breadcrumb">
			<li><a href="<c:url value='/home/index' />"><i class="icon-home"></i> 首页</a></li>
		  <li class="active"> 系统管理-类型管理</li>
		</ol>
	</div>
	<!-- 页面顶部结束 -->
	<!-- 表格开始 -->
	<div class="container-fluid">
	<input type="button" class="btn btn-default" value="添加类别" data-toggle="modal" data-target="#myModal"></input>
	<br/>
	<br/>
	</div>
<div class="container-fluid"  style="height: 450px;overflow-y:auto;">
    <table id="table"
           class="table table-bordered table-hover"
           >
        <tr>
            <th>序号</th>
            <th>类别</th>
			<th>操作</th>
        </tr>
        <tbody id="tbody">
        </tbody>
    </table>
</div>
<%@include file="../common/footer.jsp"%>
</div>

		<!-- 表格结束 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                添加类别
            </h4>
         </div>
         <form action="<c:url value='/sys/insertType' />" id="typemodel" method="post" class="form-horizontal">
         <div class="modal-body">
          <div class="form-group">
                <label for="inputName" class="col-sm-3 control-label">类别：</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="name" name="name">
                </div>
          </div>
          <div class="form-group">
                <label for="inputName" class="col-sm-3 control-label">编号：</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="sort" name="sort" onkeyup="this.value=this.value.replace(/\D/gi,'')">
                </div>
              </div>
        
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal" id="modalclose" >关闭
            </button>
            <button type="submit" class="btn btn-primary">
                   提交
            </button>
         </div>
         </form>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<div class="modal fade" id="showmodel" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                修改类别
            </h4>
         </div>
         <form action="<c:url value='/sys/updateType' />" id="typechange" method="post" class="form-horizontal">
         <div class="modal-body">
          <div class="form-group">
                <label for="inputName" class="col-sm-3 control-label">类别：</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="cname" name="name">
                  <input type="hidden" id="cid" name="id">
                </div>
              </div>
               <div class="form-group">
                <label for="inputName" class="col-sm-3 control-label">编号：</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="csort" name="sort" onkeyup="this.value=this.value.replace(/\D/gi,'')">
                </div>
              </div>

         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal" id="cmodalclose" >关闭
            </button>
            <button type="submit" class="btn btn-primary">
                   提交
            </button>
         </div>
         </form>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
	<%@include file="../common/js.jsp"%>
<script type="text/javascript">
var data;
$(document).ready(function(){
	$("#typeIndex").css({"background":"#c91306","color":"#ffffff"});
	$("#typemodel").validate({
		errorPlacement : function(error, element) {
			error.replaceAll($("#" + $(element).attr("name") + "-error"));
		},
		rules : {
			name : {
				required : true,
			},
			sort :{
				required : true,
			}
		},
		messages : {
			name : {
				required : true,
			},
			sort :{
				required : true,
			}
	
		},
		submitHandler : function() {
			$("#modalclose").click();
			
			ajaxSubmit($("#typemodel"),function(){
				successAlert("操作成功");
				$("#name").val("");
				$("#sort").val("");
				getData();
			});
		}
	});
	
	$("#typechange").validate({
		errorPlacement : function(error, element) {
			error.replaceAll($("#" + $(element).attr("name") + "-error"));
		},
		rules : {
			name : {
				required : true,
			},	
		    sort :{
			    required : true,
		    }
		},
		messages : {
			name : {
				required : true,
			},
			sort :{
				required : true,
			}
	
		},
		submitHandler : function() {
			$("#cmodalclose").click();
			$("#cname").val();
			$("#csort").val();
			ajaxSubmit($("#typechange"),function(){
				successAlert("操作成功");
				getData();
			});
		}
	});
	
	getData();
});
function getData(){
	$.ajax({
		url : "<c:url value='/sys/selectType'/>",
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.data==null)
				return;
			var html="";
			$.each(result.data,function(){
				html+="<tr><td>"+this.sort+"</td><td>"+this.name
				+"</td><td><input type='button' class='btn btn-default' value='编辑' data-toggle='modal' data-target='#showmodel' onclick=\"edit('"+this.id+"');\"></input><input type='button' style='margin-left:5px;' class='btn btn-default'  onclick=\"del('"
								+this.id+"');\"  value='删除' /></td></tr>";
			});
			$("#tbody").html(html);
			$('#table').bootstrapTable('refresh');
		}
	});
}
function edit(id){
	$.ajax({
		url : "<c:url value='/sys/getupdateType?id="+id+"'/>",
		type : 'get',
		dataType : 'json',
		success : function(result) {
			if(result.data==null)
				return;
			var id=result.data.id;
			var cname=result.data.name;
			var csort=result.data.sort;
			$("#cid").val(id);
			$("#cname").val(cname);
			$("#csort").val(csort);
		}
	});
	
	
}
function del(id){
	Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
	       if (!e) {
	         return;
	       }
	$.ajax({
		url : "<c:url value='/sys/delType?id="+id+"'/>",
		type : 'post',
		dataType : 'json',
		success : function(result) {
			successAlert("操作成功");
			getData();
		}
	});
	});
	
}
</script>
</body>
</html>
