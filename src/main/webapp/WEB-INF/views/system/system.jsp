<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../common/css.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/jquery.treeTable.css' />">

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
		  <li class="active"> 系统管理-组织管理</li>
		</ol>
	</div>
	<!-- 页面顶部结束 -->
	<!-- 表格开始 -->
	<div class="container-fluid">
	<shiro:hasPermission name="superadmin">
	<input type="button" class="btn btn-default" value="添加组织" onclick="addOrganization();"></input>
	</shiro:hasPermission>
	<shiro:hasPermission name="COMMONLYADMIN">
	<input type="button" class="btn btn-default" value="添加组织" onclick="CommonaddOrganization();"></input>
	</shiro:hasPermission>
	<br/>
	<br/>
	</div>
<div class="container-fluid"  style="height: 450px;overflow-y:auto; ">
  <table id="treeTable1" style="width:100%" class="table table-bordered table-hover  ">
    <tr>
        <td>组织树</td>
        <td>序号</td>
        <td style="width:200px">操作</td>
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
          <form id="modalForm" method="post" action="<c:url value='/sys/updateOrganization' />" class="form-horizontal">
         <div class="modal-body">
            <input type="hidden" id="oid" name="id"> 
            <input type="hidden" id="editType" name="editType">
            <input type="hidden" id="level" name="level">
              <div class="form-group">
                <label for="inputName" id="topName" class="col-sm-3 control-label">最上层单位：</label>
                <div class="col-sm-9">
                  <select class="form-control" id="toporgin"  onchange="changeLevel()">
                 
                  </select>
                </div>
              </div>
              <div class="form-group" id="typeout">
                <label for="inputName" class="col-sm-3 control-label">类型：</label>
                <div class="col-sm-9">
                  <select class="form-control" id="type" name="typeid">
                  </select>
                </div>
              </div>
             
              <div class="form-group" id="piddiv">
                <label for="inputName" class="col-sm-3 control-label">下属单位：</label>
                <div class="col-sm-9">
                  <select class="form-control" id="pid" name="pid" onchange="getlevel()">
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="inputName" class="col-sm-3 control-label">名称：</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="name" name="name">
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
	<script type="text/javascript" src="<c:url value='/resources/js/tool/jquery.treeTable.js' />"></script>
<script type="text/javascript">
var treeTable=null;
var i=0;
function reflushTable(){
	if(i){
		treeTable.clean();
		return;
	}
	i++;
	var opts = {
        theme:'vsStyle',
        expandLevel : 1,
        beforeExpand : function($treeTable, id) {
            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
            if ($('.' + id, $treeTable).length) 
            {
            	return;
            }
            $.ajax({
        		url : "<c:url value='/sys/getTheNextOrganization' />",
        		type : 'post',
        		dataType : 'json',
        		data : {
    				"pid" : id
    				},
        			success : function(result) {
        				var html="";
        			if(result.data==null)
        				return;
        			data=result.data;
        			$.each(result.data,function() {
        				html+='<tr id="'+this.id+'" pId="'+id+'" hasChild="true"><td class="first">'+this.name+'</td><td class="second">'+(this.sort==null?"":this.sort)+'</td>';
        				html+='<td><input type="button" class="btn btn-default" value="编辑" onclick="editOrganization(\''+this.id+'\',\''+this.level+'\',\''+this.typeid+'\',\''+this.sort+'\',\''+this.pid+'\',\''+this.name+'\');" ></input>\n<input type="button" class="btn btn-default" onclick="delOrganization(\''+this.id+'\');" value="删除" ></input></td></tr>';
        				
        			});
        			$treeTable.addChilds(html);
        		}
        	});
            
        },
        onSelect : function($treeTable, id) {
            window.console && console.log('onSelect:' + id);
        }
		};
   
	treeTable=$("#treeTable1").treeTable(opts);
    }    

function getdata(){
	$.ajax({
		url : "<c:url value='/sys/getTheOrganization' />",
		type : 'post',
		dataType : 'json',
			success : function(result) {
			if(result.data==null)
				return;
			var html="";
			data=result.data;
			$.each(result.data,function() {
				html+='<tr id='+this.id+' hasChild="true" ><td class="first">'+this.name+'</td><td class="second">'+(this.sort==null?"":this.sort)+'</td>';
				if(this.isdef==0){
				html+='<td></td></tr>';
				}else{
				html+='<td><input type="button" class="btn btn-default" value="编辑" onclick="editOrganization(\''+this.id+'\',\''+this.level+'\',\''+this.typeid+'\',\''+this.sort+'\',\''+this.pid+'\',\''+this.name+'\');" ></input>\n<shiro:hasPermission name="superadmin"><input type="button" class="btn btn-default" onclick="delOrganization(\''+this.id+'\');" value="删除" ></input></shiro:hasPermission></td></tr>';
				}
			});
			$("#tbody").html(html);
			reflushTable();
		}
	});
	
	
}

$(document).ready(function(){
	$("#index").css({"background":"#c91306","color":"#ffffff"});
		$("#modalForm").validate({
		errorPlacement : function(error, element) {
			error.replaceAll($("#" + $(element).attr("name") + "-error"));
		},
		rules : {
			name : {
				required : true,
			}
		},
		messages : {
			name : {
				required : true,
			}
		},
		submitHandler : function() {
			$("#modalclose").click();
	
			ajaxSubmit($("#modalForm"),function(){
			successAlert("操作成功");
				getType();
				getdata();
				 
			});
			
		}
		
	});
		getdata();
		getType();	
});

function getType(){
	$.ajax({
		url : "<c:url value='/sys/selectType' />",
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.data==null)
				return;
			var html="<option value='0'></option>";
			data=result.data;
			$.each(result.data,function() {
				html+="<option value='"+this.id+"' >"+this.name+"</option>";
			});
			$("#type").html(html);
		}
	});
}
function getFirstOrganize(){
	$.ajax({
		url : "<c:url value='/sys/getFirstOrganize' />",
		type : 'post',
			dataType : 'json',
			success : function(result) {
			if(result.data==null)
				return;
			var html='<option value="0"></option>';
			data=result.data;
			$.each(result.data,function() {
				html+='<option value='+this.id+'>'+this.name+'</option>';
			});
			$("#toporgin").html(html);
			
		}
	});
}
function changeLevel(){
	i=$("#toporgin").val();
	if(i==0){
		$("#pid").html("<option value='0'></option>");
		$("#level").val(0);
		$("#piddiv").hide();
		$("#typeout").show();
	}else{
		var html="<option value='"+i+"'><div></div></option>";
		$.ajax({
			url:"<c:url value='/sys/getallChildOrganize'/>",
			type : 'post',
			dataType:'json',
			data:{
				pid:i
			},
			success:function(result){
			
			$.each(result.data,function(){
				html+="<option value='"+this.id+"'><div>"+this.name+"</div></option>";
			});	
			$("#level").val(1);
			$("#pid").html(html);
			$("#piddiv").show();
			$("#typeout").hide();
			}
		});
		
		
	}
}

function getlevel(){
	var pid=0;
	pid=$("#pid").val();
	$.ajax({
		url:"<c:url value='/sys/getupOrganize' />",
		type : 'post',
		dataType : 'json',
		data:{
			id:pid
		},
		success : function(result) {
		if(result.data==null)
			return;
		    
		   $("#level").val(result.data.level+1);
		
		
		}
	    
	});
}
function addOrganization(){
	clean();
	getFirstOrganize();
	$("#topName").html("最上级单位：");
	$("#modalTitle").html("添加");
	$("#editType").val("add");
	$("#typeout").show();
	$("#modalOpen").click();
}
function CommonaddOrganization(){
	clean();
	$.ajax({
		url : "<c:url value='/sys/getSelfOrganize' />",
		type : 'post',
			dataType : 'json',
			success : function(result) {
			if(result.data==null)
				return;
			var html='';
			data=result.data;		    
				html+='<option value='+data.id+'>'+data.name+'</option>';
			$("#toporgin").html(html);
			
			i=$("#toporgin").val();
			var html="<option value='"+i+"'><div></div></option>";
			$.ajax({
				url:"<c:url value='/sys/getallChildOrganize'/>",
				type : 'post',
				dataType:'json',
				data:{
					pid:i
				},
				success:function(result){
				
				$.each(result.data,function(){
					html+="<option value='"+this.id+"'><div>"+this.name+"</div></option>";
				});	
				$("#level").val(1);
				$("#pid").html(html);
				$("#piddiv").show();
				$("#typeout").hide();
				}
			});
			
		}
	});
	$("#topName").html("最上级单位：");
	$("#modalTitle").html("添加");
	$("#editType").val("add");
	$("#typeout").show();
	$("#modalOpen").click();
	
	
}

function editOrganization(id,level,typeid,sort,pid,name){
	clean();
	$("#topName").html("上级单位");
	$("#modalTitle").html("编辑");
	$("#editType").val("edit");
	$("#level").val(level);
	$("#oid").val(id);
	$("#pid").html("");
	$("#toporgin").attr("disabled",true);
	$("#type").val(typeid);
	if(sort!="null"){
		 $("#csort").val(sort);
	}
	if(level==0){
		$("#typeout").show();
	}
	if(level!=0){
		$("#typeout").hide();
	}
	$.ajax({
		url : "<c:url value='/sys/getupOrganize' />",
		type : 'post',
			dataType : 'json',
			data:{
				id:pid
			},
			success : function(result) {
			if(result.data==null)
				return;
			var data=result.data;
			
			
			var html='<option value='+data.id+'>'+data.name+'</option>';
			$("#toporgin").html(html);
	
			$("#name").val(name);
			$("#modalOpen").click();
			}
	});
	

	
}

function delOrganization(id){
	Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
	       if (!e) {
	         return;
	       }
	       $.ajax({
				url : "<c:url value='/sys/delOrganization' />",
				type : 'post',
				dataType : 'json',
				data : {
					"id" : id
					},
				success : function(result) {
					successAlert("删除成功");
					getType();
					getdata();
				}
				});
	 });
	
}
function clean(){
	$("#modalForm input").each(function(index){
		$(this).val("");
	});
	$("#pid").html("<option value='0'></option>");
	$("#piddiv").hide();
	$("#level").val(0);
	$("#toporgin").val(0);
	$("#toporgin").attr("disabled",false);
}
</script>
</body>
</html>
