<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<%@include file="../common/css.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/tool/zTreeStyle/zTreeStyle.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/common/ztree.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common/docAudit.css'/>">
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
		  <li class="active"> 系统管理-公告管理</li>
		</ol>
	</div>
	<!-- 页面顶部结束 -->
	<!-- 表格开始 -->
	<div class="container-fluid">
	<input type="button" class="btn btn-default" value="添加公告" data-toggle="modal" data-target="#myModal"></input>
	<br/>
	<br/>
	</div>
<div class="container-fluid"  style="height: 450px;overflow-y:auto;">
    <table id="table"
           class="table table-bordered table-hover"
           >
        <tr>
            <th>序号</th>
            <th>发布时间</th>
			<th>标题</th>
			<th>详细信息</th>
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
         
          <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
         <div class="modal-title tab-caption">发布公告</div>
         
        <div class="container-fluid">
			<div class="row">
				<div class="col-md-8 col-sm-8">
					<form id="noticeform" action="<c:url value='/sys/insertNotice' />"
						method="post" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-3">
								<strong class="pull-right">接收单位：</strong>
							</div>
							<div class="col-md-9">
								<div id="jsdwText" class="jsdwText"></div>
							</div>
							<hr />
						</div>
						<hr />

						<div class="row">
								<div class="form-horizontal">
									<div class="form-group">
										<label for="title" class="col-xs-3 control-label">标题：</label>
										<div class="col-xs-9">
											<input type="text" class="form-control" id="title"
												name="title">
										</div>
									</div>
							</div>
						</div>
						<hr />

						<div class="row">
							<div class="col-md-12">
								<div class="form-horizontal">
									<div class="form-group">
										<label for="docSummary1" class="col-md-3 control-label">内容：</label>
										<div class="col-md-9">
											<textarea class="form-control" id="summary"
												name="summary"></textarea>
										</div>

									</div>
								</div>
							</div>
						</div>
						<hr />
						<div class="row">
							<div class="col-md-3">
								<strong class="pull-right">上传文件：</strong>
							</div>
							<div class="col-md-9">
								<div class="btn btn-default" id="btn_add1">
									<b>添加附件</b>
								</div>
								<div class="btn btn-default">
									<b>扫描</b>
								</div>
							</div>
							<hr />
							<div id="fj" class="col-md-10 col-md-offset-2"></div>
						</div>

						<div class="modal-footer">
						<button type="button" class="btn btn-default" 
						data-dismiss="modal" id="cmodalclose" >关闭
						</button>
						<button type="submit" class="btn btn-primary">提交</button>
						</div>
					</form>
				</div>
				<div class="col-md-4 col-sm-4 treeBox">
					<div class="treebox">
						<div class="title">
							常用单位 <a style="float:right;" data-toggle="modal" id="managerclick"
								data-target="#manager" href="#" onclick="getMagroup()">管理</a>
						</div>
						<div id="cydw" class="content cydw">
							
						</div>
						<div class="title">全部单位</div>
						<div class="content">
							<div id="ztreeBox" class="ztree"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
        
        
        
        
        
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

<div class="modal fade" id="showModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog docmodel">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
              	 详情
            </h4>
         </div>
         <div class="modal-body">
            <div class="docmain">
            <div class="title" id="department">  
               </div>
               <div class="line">
                 <div class="block">
                 	<div class="first" > 
                 	公告时间：
                 	</div>
                 	<div class="second" id="show_createtime">
                 	
                 	</div>
                 </div>
               </div>
               <div class="title" id="show_department">
               </div>
               <div class="title" id="show_title">
               </div>
               <div class="title_b" id="show_summary">
               </div>    
               <div class="line">
               <div class="first"> 
                	   附件：
               </div>
               <div class="fjtable">
                 <table class="table table-bordered " id="show_attach">
                                     
                 </table>
               </div>   
               </div>
            </div>
     
            <div class="clear"></div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
         </div>
      </div><!-- /.modal-content -->
      <input type="hidden" value="" id="pages"/>
</div>
</div>

<div class="modal fade" id="manager" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog docmodel">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">常用单位管理</h4>
				</div>
				<div class="modal-body">
					<div class="docmain">
						<form action="" method="post" id="dothings">
							<table class="table table-bordered table-hover changemanager ">
								<tr class="tdmiddle">
									<th style="text-align:center;">编号</th>
									<th style="text-align:center;">常用单位组</th>
									<th style="text-align:center;">操作</th>
								</tr>
								<tbody id="Magroups" style="border:0px solid #2d2d2d;">

								</tbody>
							</table>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button id="addMagroup" type="button" class="btn btn-default leftbutton"
						data-toggle="modal" data-target="#managerinfo" onclick="clean();">添加
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>

<div class="modal fade" id="managerinfo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="<c:url value='/doc/insertMagroup' />" id="managerform" method="post">
		<div class="modal-dialog docmodel">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="managerinfotitle">组别详情</h4>
				</div>
				<div class="modal-body">
				<input id="managerformid" type="hidden" value="">
				<input name="type" type="hidden" value="1">
				
				<div class="form-group">
								<label for="inputName" class="col-sm-3 control-label" style="margin-left:-15px;">组别名称：</label>
                				<div class="col-sm-9">
                 				 <input type="text" class="form-control" id="zname" name="name">
               			 </div>
               	</div>
               	<div class="form-group" style="margin-top:40px;">
								<label for="inputName" class="col-sm-3 control-label" style="margin-left:-15px;">编号：</label>
                				<div class="col-sm-9">
                 				 <input type="text" class="form-control" id="zsort" name="sort" onkeyup="this.value=this.value.replace(/\D/gi,'')">
               			 </div>
               	</div>
               	<div class="form-group">
					<div class="managerinfoblock">
							<div class="form-group">
								<label for="name">组内人员:</label>
								<div id="mjsdwText" class="mjsdwText" ></div>
						
							</div>
					</div>
					<div class="managerinfoblock">
					<div class="managerinfoblockright" >
					  <div class="treebox" style="max-height:205px;min-height: 205px;">
						<div class="title">
							单位列表 
						</div>
						
							<div class="content" style="height:175px;overflow-y:auto;">
							<div id="mztreeBox" class="ztree">
							</div> 
						</div> 
						
						
					</div>
					  </div>
					</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger leftbutton" id="modeldismiss"
						data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-success">确定</button>
				</div>
				</form>
			</div>
	<%@include file="../common/js.jsp"%>
	<script type="text/javascript"
		src='<c:url value="/resources/js/common/ztree.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/tool/jquery.ztree.core.js"/>'></script>
<script type="text/javascript">
var i=1;
var ztreeObj;
var setting = {
		view : {
			showIcon : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode) {
				var flag = judgeExist($("#jsdwText input"), treeNode.id);
				if (!flag) {
					var html = insertSelectedDw(treeNode.id, treeNode.name);
					$("#jsdwText").append(html.join(''));
					if ($("#jsdwText").children().length >= 1) {
						$("button[class='close closethat']").on("click", function(e) {
							$(this).parent().remove();
							isFlag = true;
						});
					}
				}
			}
		}
	};
var settingtwo = {
		view : {
			showIcon : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode) {
				var flag = judgeExist($("#mjsdwText input"), treeNode.id);
				if (!flag) {
					var html = insertSelectedDwtwo(treeNode.id, treeNode.name);
					$("#mjsdwText").append(html.join(''));
					if ($("#mjsdwText").children().length >= 1) {
						$("button[class='close closethat']").on("click", function(e) {
							$(this).parent().remove();
							isFlag = true;
						});
					}
				}
			}
		}
	};
$(document).ready(function(){
	$("#noticeIndex").css({"background":"#c91306","color":"#ffffff"});
	getData();
	$("#btn_add1").click(function(){
		$("#fj").append('<div id="fjdiv_'+i+'"><div class="col-md-8"><input  type="file" name="upfile" /></div><div class="col-md-4"><div class="btn btn-default" onclick="del_1('+i+')"><b>删除</b></div></div></div>');
				  i = i + 1;
		});
	$.post('<c:url value="/sys/getOrganizationTree" />',
			{}, function(data) {
			ztreeObj = $.fn.zTree.init($("#ztreeBox"),setting, data.data);
			}, "json");
	$.post('<c:url value="/sys/getOrganizationTree" />',{}, function(data) {
		ztreeObj = $.fn.zTree.init($("#mztreeBox"),settingtwo, data.data);}, "json");
	getMagroup();
	
	$("#noticeform").validate({
		errorPlacement : function(error, element) {
			error.replaceAll($("#" + $(element).attr("name") + "-error"));
		},
		rules : {
			department : {
				required : true
			},
			title : {
				required : true
			},
			summary : {
				required : true
			}
		},
		messages : {
			department : {
				required : true
			},
			title : {
				required : true
			},
			summary : {
				required : true
			}
		},
		submitHandler : function() {
			ajaxSubmit(noticeform, function(data){
				$("#jsdwText").html("");
				$("#title").val("");
				$("#summary").val("");
				$("#fj").html("");
				$("#myModal").modal("hide");
				getData();
			});
		}
	});
	
	
});
function del_1(o) {
	document.getElementById("fj").removeChild(document.getElementById("fjdiv_" + o));
}
function getData(){
	$.ajax({
		url : "<c:url value='/sys/getAdminNoticeList'/>",
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.data==null)
				return;
			var html="";
			var i=1;
			$.each(result.data,function(){
				 var date=new Date(this.createTime);
				 var month=date.getMonth()+1;
				 var minutes=""+date.getMinutes();
				 var seconds=""+date.getSeconds();
				 if(minutes.length==1){
					 minutes="0"+minutes;
				 }
				 if(seconds.length==1){
					 seconds="0"+seconds;
				 }
				 var datetime=date.getFullYear()+"/"+month+"/"+date.getDate()+" "+date.getHours()+":"+minutes+":"+seconds;
				html+="<tr><td>"+i+++"</td><td>"+datetime+"</td><td>"+this.title+"</td><td><a href='#' onclick='showNotice(\""+this.id+"\");' > 查看</a>"
				+"</td><td><input type='button' style='margin-left:5px;' class='btn btn-default'  onclick=\"del('"
								+this.id+"');\"  value='删除' /></td></tr>";
			});
			$("#tbody").html(html);
			$('#table').bootstrapTable('refresh');
		}
	});
}
function showNotice(id){
	$.ajax({
		url : "<c:url value='/sys/getNotice'/>",
		type : 'post',
		dataType : 'json',
		data : {"id" : id},
		success : function(result) {
			var date=new Date(result.data.createTime);
			 var month=date.getMonth()+1;
			 var minutes=""+date.getMinutes();
			 var seconds=""+date.getSeconds();
			 if(minutes.length==1){
				 minutes="0"+minutes;
			 }
			 if(seconds.length==1){
				 seconds="0"+seconds;
			 }
			 var datetime=date.getFullYear()+"/"+month+"/"+date.getDate()+" "+date.getHours()+":"+minutes+":"+seconds;
			$("#show_createtime").html(datetime);
			$("#show_department").html("<b>接收单位：</b>&nbsp;"+result.data.departmentName);
			$("#show_title").html("<b>标题：</b>&nbsp;"+result.data.title);
			$("#show_summary").html("<b>内容：</b>&nbsp;"+result.data.summary);
			$.ajax({
				url : "<c:url value='/doc/getGGAttach' />",
				type : 'post',
				dataType : 'json',
				data : {"pid" : id},
				success : function(result) {
					var html="";
					 $.each(result.data,function() {
						 var fileurl="<c:url value='/attachment/download?id=" + this.id + "'/>";
						 html+="<tr><td>"+this.fileName+"</td><td><a target=\"_blank\" href='"+fileurl+"'>查看</a></td></tr>";
					 });
					 $("#show_attach").html(html);
					 $("#showModal").modal("show");
				}
			});
		}
	});
}

function del(id){
	Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
	       if (!e) {
	         return;
	       }
	$.ajax({
		url : "<c:url value='/sys/delNotice'/>",
		type : 'post',
		dataType : 'json',
		data : {"id" : id},
		success : function(result) {
			successAlert("操作成功");
			getData();
		}
	});
	});
	
}
$("#managerform").validate({
	errorPlacement : function(error, element) {
		error.replaceAll($("#" + $(element).attr("name") + "-error"));
	},
	rules : {
		name : {
			required : true
		}
	},
	messages : {
		name : {
			required : true
		}
	},
	submitHandler : function() {
		var id=$("#managerformid").val()+"";
		$("#modeldismiss").click();
		if(id=="0"){
			var url="<c:url value='/doc/insertMagroup' />";
			$("#managerform").attr("action",url );
		}else{
			var url="<c:url value='/doc/updateMagroup?id="+id+"' />";
			$("#managerform").attr("action",url );
		}
		ajaxSubmit(managerform, getMagroup);
		clean();
	}
});

function getMagroup(){
	$.ajax({
		url : "<c:url value='/doc/getMagroupAll?select=all' />",
		type : 'post',
		dataType : 'json',
		data:{
			type:1
		},
		success : function(result) {
			var html="";
			var htmltwo="";
			 $.each(result.data,function() {
				 var sort=this.sort;
				 if(sort==null){sort="";}
				 html+="<tr><td>"+sort+"</td><td>"+this.name+"</td><td><a href='#' onclick=\"change('"+this.id+"')\">修改</a>&nbsp;&nbsp;<a href='#' onclick=\"deletethis('"+this.id+"')\">删除</a></td></tr>";
				 htmltwo+="<div id="+this.id+" onclick=\"addleft('"+this.id+"')\">"+this.name+"</div>";
			 });
			 $("#Magroups").html(html);
			 $("#cydw").html(htmltwo);
		}
	});
	
}
function clean(){ 
	$("#zname").val("");
	$("#managerformid").val("0");
	$("#zsort").val("");
	$("#mjsdwText").html("");
	
}
function deletethis(id){
	var url="<c:url value='/doc/deletethis?id="+id+"'/>";
	$("#dothings").attr("action",url);
	ajaxSubmit($("#dothings"),getMagroup);
}

function change(id){
	clean();
	$("#addMagroup").click();
	selectone(id,"mjsdwText");
}

function addleft(id){
	selectone(id,"jsdwText");
	
}
function selectone(id,obj){
	var objid="#"+obj;
	$.ajax({
		url : "<c:url value='/doc/getMagroupAll?select=one '/>",
		type : 'post',
		dataType : 'json',
		data:{
			id:id
		},
		success : function(result) {
			var name=result.data.name;
			var id=result.data.id;
			var value=result.data.value;
			if(obj=="mjsdwText"){
			var sort=result.data.sort;
			$("#zname").val(name);
			$("#managerformid").val(id);
			if(sort=="null"){
			sort="";	
			}
			$("#zsort").val(sort);
			}
			var ids=value.split(",");
			var deps=result.data.departmentName.split(",");
			for (var i=0;i<ids.length;i++) 
			{ 	
				var flag = judgeExist($(objid+" input"), ids[i]);
				if (!flag) {
					var html = "";
					if(obj=="jsdwText"){
						html = insertSelectedDw(ids[i], deps[i]);
					}else{
						html = insertSelectedDwtwo(ids[i], deps[i]);
					}
					$(objid).append(html.join(''));
					if ($(objid).children().length >= 1) {
						$("button[class='close closethat']").on("click", function(e) {
							$(this).parent().remove();
						});
					}
				
				}
			}
			

		}
	});
	
}
</script>
</body>
</html>
