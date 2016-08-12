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
		  <li class="active"> 系统管理-联系人管理</li>
		</ol>
	</div>
	<!-- 页面顶部结束 -->
	<!-- 表格开始 -->
	<div class="container-fluid">
	<input type="button" class="btn btn-default" value="添加联系人" onclick="addUser();"></input>
	<br/>
	<br/>
	</div>
<div class="container-fluid"  style="height: 450px;overflow-y:auto; ">
    <table id="table"
           class="table table-bordered table-hover"
           >
        <tr>
            <th>序号</th>
            <th>所属单位</th>
            <th>所属部门</th>
            <th>所属科室</th>
            <th>姓名</th>
            <th>联系电话一</th>
            <th>联系电话二</th>
            <th>联系电话三</th>
            <th>是否为常用联系人</th>
			<th>操作</th>
        </tr>
        <tbody id="tbody">
        </tbody>
    </table>
</div>
		<!-- 表格结束 -->
<%@include file="../common/footer.jsp"%>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <input id="modalOpen" style="display:none" type="button" data-toggle="modal" data-target="#myModal">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 id="modalTitle" class="modal-title" id="myModalLabel">
               添加
            </h4>
         </div>
         <form id="modalForm" method="post" action="<c:url value='/sys/updateUser' />" class="form-horizontal">
         <div class="modal-body">
            <input type="hidden" id="uid" name="id">
            <input type="hidden" id="editType" name="editType">
              <div class="form-group">
                <label for="oid" class="col-sm-3 control-label">所属单位：</label>
                <div class="col-sm-9">
                  <select class="form-control" id="oid" name="oid">
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="name" class="col-sm-3 control-label">姓名：</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="name" name="name">
                </div>
              </div>
              <div class="form-group">
                <label for="phone" class="col-sm-3 control-label">联系电话一：</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="phone" name="phone">
                </div>
              </div>
              <div class="form-group">
                <label for="phone2" class="col-sm-3 control-label">联系电话二：</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="phone2" name="phone2">
                </div>
              </div>
              <div class="form-group">
                <label for="phone3" class="col-sm-3 control-label">联系电话三：</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="phone3" name="phone3">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="ismain">常用联系人：</label>
                <label class="radio-inline">
				  <input type="radio" value="0" id="ismain2" name="ismain" /> 是
				</label>
				<label class="radio-inline">
				  <input type="radio" value="1" id="ismain" name="ismain" /> 否
				</label>
              </div>
         </div>
         <div class="modal-footer">
            <button id="modalclose" type="button" class="btn btn-default"  
               data-dismiss="modal">关闭
            </button>
            <button id="submit" type="submit" class="btn btn-primary">
               提交
            </button>
         </div>
          </form>
      </div>
	</div>
	</div>
	<%@include file="../common/js.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#userIndex").css({"background":"#c91306","color":"#ffffff"});
	setOrganization();
	$("#modalForm").validate({
		errorPlacement : function(error, element) {
			error.replaceAll($("#" + $(element).attr("name") + "-error"));
		},
		rules : {
			name : {
				required : true,
			},
			phone : {
				required : true,
			}
		},
		messages : {
			name : {
				required : true,
			},
			phone : {
				required : true,
			}
		},
		submitHandler : function() {
			$("#modalclose").click();
			ajaxSubmit($("#modalForm"),function(){
				successAlert("操作成功");
				getData();
			});
		}
	});
	getData();
	
});

function setOrganization(){
	$.ajax({
		url : "<c:url value='/sys/getOrganization' />",
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.data==null)
				return;
			html="";
			$.each(result.data,function() {
				if(this.isdef!=0){
					html+="<option value='"+this.id+"'><div>"+this.name+"</div></option>";
				}
				if(this.organizationList!=null){
					$.each(this.organizationList,function() {
						html+="<option value='"+this.id+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+this.name+"</option>";
						if(this.organizationList!=null){
							$.each(this.organizationList,function() {
								html+="<option value='"+this.id+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+this.name+"</option>";
							});
						}
					});
				}
			});
			$("#oid").html(html);
		}
	});
}
function getData(){
	$.ajax({
		url : "<c:url value='/sys/getUser' />",
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.data==null)
				return;
			var html="";
			var i=1;
			$.each(result.data,function() {
				var orgname="";
				var orgid="";
				var oid="";
				if(this.orgname!=null){
					 orgname=(this.orgname).split(",");
					 orgid=(this.orgid).split(",");
					 oid=orgid[orgid.length-1];
				}
				html+="<tr>";
				html+="<td>"+i+++"</td>";
				if(orgname[2]!=null){
					html+="<td>"+orgname[0]+"</td>";
					html+="<td>"+orgname[1]+"</td>";
					html+="<td>"+orgname[2]+"</td>";
				}else if(orgname[1]!=null){
					html+="<td>"+orgname[0]+"</td>";
					html+="<td>"+orgname[1]+"</td>";
					html+="<td></td>";
				}else if(orgname[0]!=null){
					html+="<td>"+orgname[0]+"</td>";
					html+="<td></td>";
					html+="<td></td>";
				}else {
					html+="<td></td>";
					html+="<td></td>";
					html+="<td></td>";
					
				}
				html+="<td class='name'>"+this.name+"</td>";
				if(this.phone!=null){
					html+="<td class='phone'>"+this.phone+"</td>";
				}else{
					html+="<td class='phone'></td>";
				}
				if(this.phone2!=null){
					html+="<td class='phone2'>"+this.phone2+"</td>";
				}else{
					html+="<td class='phone2'></td>";
				}
				if(this.phone3!=null){
					html+="<td class='phone3'>"+this.phone3+"</td>";
				}else{
					html+="<td class='phone3'></td>";
				}
				html+="<td>"+(this.ismain==0?"是":"否")+"</td>";
				if(this.id==1){
					html+='<td></td>';
				}else{
					html+='<td><input type="button" class="btn btn-default" value="编辑" onclick="editUser(\''+this.id+'\',\''+this.ismain+'\',\''+oid+'\',this);" ></input>\n<input type="button" class="btn btn-default" onclick="delUser(\''+this.id+'\');" value="删除" ></input></td>';
				}
				html+="</tr>";
			});
			$("#tbody").html(html);
			$('#table').bootstrapTable('refresh');
		}
	});
}
function addUser(id,level){
	clean();
	$("#modalTitle").html("添加");
	$("#editType").val("add");
//	$("#level").val(level);
	$("#modalOpen").click();
}
function editUser(id,ismain,oid,obj){
	clean();
	$("#modalTitle").html("编辑");
	$("#editType").val("edit");
	$("#uid").val(id);
	$("#oid").val(oid);
	$("#name").val($(obj).parent().parent().children(".name").html());
	$("#phone").val($(obj).parent().parent().children(".phone").html());
	$("#phone2").val($(obj).parent().parent().children(".phone2").html());
	$("#phone3").val($(obj).parent().parent().children(".phone3").html());
	if(ismain==0){
		$("#ismain2").click();
	}else if(ismain==1){
		$("#ismain").click();
	}
	$("#modalOpen").click();
	
}

function delUser(id){
	Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
	       if (!e) {
	         return;
	       }
	       $.ajax({
				url : "<c:url value='/sys/delUser' />",
				type : 'post',
				dataType : 'json',
				data : {
					"id" : id
					},
				success : function(result) {
					successAlert("删除成功");
					getData();
				}
				});
	 });
	
}
function clean(){
	$("#modalForm input").each(function(index){
		$(this).val("");
	});
	$("#ismain").val(1);
	$("#ismain2").val(0);
	$("#oid").val("");
	$("#ismain").click();
}
</script>
</body>
</html>
