<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<%@include file="../common/css.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.animation.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.widget.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.widget.ios.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.scroller.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.scroller.ios.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/zTreeStyle/zTreeStyle.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common/ztree.css'/>">

<!-- The styles -->
<style type="text/css">
</style>

</head>

<body>
	<%@include file="../common/header.jsp"%>

	<%@include file="../common/nav.jsp"%>
	
	<div class="col-lg-10 index-iframe" >
	<div class="docInput-top">
		<ol class="breadcrumb">
			<li><a href="<c:url value='/home/index' />"><i class="icon-home"></i> 首页</a></li>
		  <li class="active"> 会议发布</li>
		</ol>
	</div>
	<!-- 页面顶部结束 -->
	
	<!-- 表格开始 -->
	<div class="container-fluid">
	<div class="row">
			<div class="col-md-12 tab-caption">会 议 通 知</div>
		</div>
	<div class="row">
	<div class="col-md-9 col-sm-9">
	<form id="meetform" action="<c:url value='/meet/meetSend' />" method="post" enctype="multipart/form-data" >
		
		<div class="row">
			<div class="col-md-4">
				<div class="form-horizontal">
				  <div class="form-group">
				    <label for="meetTimeString" class="col-xs-6 control-label">会议时间：</label>
						<div class="col-xs-6">
				    		<input type="text" class="form-control" id="meetTimeString" name="meetTimeString" value="">
				    	</div>
				  </div>
				</div>
			</div>
	  		<div class="col-md-4">
	  			<div class="form-horizontal">
					  <div class="form-group">
					    <label for="name" class="col-xs-6 control-label">会议名称：</label>
					    <div class="col-xs-6">
					    	<input type="text" class="form-control" id="name" name="name">
					    </div>
					  </div>
				</div>
	  		</div>
	  		<div class="col-md-4">
				<div class="form-horizontal">
					<div class="form-group">
					    <label for="meetCompany4" class="col-xs-6 control-label">承办单位：</label>
						<div class="col-xs-6">
					    	<select class="form-control" id="meetCompany" name="meetCompany">
					    	</select>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="row">
				<div class="col-md-2">
					<strong class="pull-right">接收单位：</strong>
				</div>
				<div class="col-md-10">
					<div id="jsdwText" class="jsdwText">
					</div>
				</div>
				<hr/>
			</div>
        <hr/>
		<div class="row">
			<div class="col-md-4">
					<div class="form-horizontal">
					  <div class="form-group">
					    <label for="level4" class="col-xs-6 control-label">等&nbsp; &nbsp; &nbsp; &nbsp;级：</label>
					    <div class="col-xs-6">
						    <select class="form-control" id="level" name="level">
						        <option value=></option>
				  				<option value=1>特急</option>
								<option value=2>加急</option>
								<option value=3>平急</option>
								<option value=4>特提</option>
				  			</select>
				  		</div>
					  </div>
					</div>
			</div>
			<div class="col-md-4">
				<div class="form-horizontal">
				  <div class="form-group">
				    <label for="docNo" class="col-xs-6 control-label">发文编号：</label>
							<div class="col-xs-6">
				    		<input type="text" class="form-control" id="docNo" name="docNo">
				    	</div>
				  </div>
				</div>
			</div>
  		<div class="col-md-4">
  			<div class="form-horizontal">
				  <div class="form-group">
				    <label for="docTitle" class="col-xs-6 control-label">发文标题：</label>
				    <div class="col-xs-6">
				    	<input type="text" class="form-control" id="docTitle" name="docTitle">
				    </div>
				  </div>
				</div>
  		</div>
		</div>

		<div class="row">
			<div class="col-md-4">
				<div class="form-horizontal">
				  <div class="form-group">
				    <label for="contactName" class="col-xs-6 control-label">联 系 人：</label>
							<div class="col-xs-6">
				    		<input type="text" class="form-control" id="contactName" name="contactName">
				    	</div>
				  </div>
				</div>
			</div>
  		<div class="col-md-4">
  			<div class="form-horizontal">
				  <div class="form-group">
				    <label for="contactPhone" class="col-xs-6 control-label">联系电话：</label>
				    <div class="col-xs-6">
				    	<input type="text" class="form-control" id="contactPhone" name="contactPhone">
				    </div>
				  </div>
				</div>
  		</div>
  		
  		<div class="col-md-4">
  			<div class="form-horizontal">
				  <div class="form-group">
				    <label for="addr" class="col-xs-6 control-label">地&nbsp; &nbsp; &nbsp; &nbsp;址：</label>
				    <div class="col-xs-6">
				    	<input type="text" class="form-control" id="addr" name="addr">
				    </div>
				  </div>
				</div>
  		</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="form-horizontal">
				  <div class="form-group">
				    <label for="docSummary" class="col-md-2 control-label">发文摘要：</label>
							<div class="col-md-10">
				    		<textarea type="text" class="form-control" id="docSummary" name="docSummary"></textarea>
				    	</div>
				  </div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-2">
				<strong class="pull-right">上传文件：</strong>
			</div>
			<div class="col-md-10">
				<div class="btn btn-default" id="btn_add1"><b>添加附件</b></div>
				<div class="btn btn-default"><b>扫描</b></div>
			</div>
			<hr/>
			<div id="fj" class="col-md-10 col-md-offset-2"></div>
		</div>

		<div class="row">
			<div class="col-md-10 col-md-offset-2">
				<br/>
				<button type="submit" class="btn btn-default btn-lg"><strong>发 送</strong></button>
			</div>
		</div>
		</form>
	</div>
	<div class="col-md-3 col-sm-3 treeBox">
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
				<input name="type" value="0" type="hidden">
				
				<div class="form-group">
								<label for="inputName" class="col-sm-3 control-label" style="margin-left:-15px;">组别名称：</label>
                				<div class="col-sm-9">
                 				 <input type="text" class="form-control" id="cname" name="name">
               			 </div>
               	</div>
               	<div class="form-group" style="margin-top:40px;">
								<label for="inputName" class="col-sm-3 control-label" style="margin-left:-15px;">编号：</label>
                				<div class="col-sm-9">
                 				 <input type="text" class="form-control" id="csort" name="sort" onkeyup="this.value=this.value.replace(/\D/gi,'')">
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
	<%@include file="../common/footer.jsp"%>
	</div>
	<%@include file="../common/js.jsp"%>
	<script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.core.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.widget.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.scroller.js' />"></script>
    

    <script src="<c:url value='/resources/js/tool/mobiscroll.util.datetime.js' />"></script>
    <script src="<c:url value='/resources/js/tool/mobiscroll.datetimebase.js' />"></script>

    <script src="<c:url value='/resources/js/tool/mobiscroll.widget.ios.js' />"></script>

    <script src="<c:url value='/resources/js/tool/mobiscroll.i18n.zh.js' />"></script>
    <script type="text/javascript" src='<c:url value="/resources/js/common/ztree.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/tool/jquery.ztree.core.js"/>'></script>
    <script type="text/javascript">
        var i = 1;
        var option="";
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
        function del_1(o){
        	
			document.getElementById("fj").removeChild(document.getElementById("fjdiv_"+o));
		}
        function setOrganization(){
			$.ajax({
				url : "<c:url value='/sys/getOrganizationData' />",
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
					option=html;
					$("#meetCompany").html(option);
				}
			});
		}
        $("#cydw div").on("click",function(){
    		var id = $(this).attr("id");
    		var text = $(this).text();
    		var flag = judgeExist($("#jsdwText input"),id);
    		if(!flag){
    			var html = insertSelectedDw(id,text);
    			$("#jsdwText").append(html.join(''));
    			if($("#jsdwText").children().length >= 1){
    				$("button[class='close']").on("click",function(e){
    					$(this).parent().remove();
    				});
    			}
    		}
    	});
   
    	var ztreeObj;
    	$(document).ready(function(){
    		$("#meetInputIndex").css({"background":"#c91306","color":"#ffffff"});
    		var opt= { 
    	        	theme:'ios', //设置显示主题 
                    mode:'scroller', //设置日期选择方式，这里用滚动
                    display:'modal', //设置控件出现方式及样式
                    preset : 'datetime', //日期:年 月 日 时 分
                    dateFormat: 'yy-mm-dd', // 日期格式
                    dateOrder: 'yymmdd', //面板中日期排列格式
                    stepMinute: 1, //设置分钟步长
                    yearText:'年', 
                    monthText:'月',
                    dayText:'日',
                    hourText:'时',
                    minuteText:'分',
                    lang:'zh' //设置控件语言};
                };
                $('#meetTimeString').mobiscroll(opt);
                setOrganization();
                $("#meetform").validate({
        			errorPlacement : function(error, element) {
        				error.replaceAll($("#" + $(element).attr("name") + "-error"));
        			},
        			rules : {
        				name : {
        					required : true
        				},
        				meetCompany : {
        					required : true
        				},
        				department : {
        					required : true
        				},
        				docTitle : {
        					required : true
        				}
        			},
        			messages : {
        				name : {
        					required : true
        				},
        				meetCompany : {
        					required : true
        				},
        				department : {
        					required : true
        				},
        				docTitle : {
        					required : true
        				}
        			},
        			/* success : function(label) {
        				label.text("${validate_message_ok}").addClass("valid");
        				label.removeClass("error");
        				label.html("有效");
        				return false;
        			}, */
        			submitHandler : function() {
        				var num=$('#jsdwText').children('.marginR10');
        				if($("#meetTimeString").val()==''){
        					$("#meetTimeString").click();
        				}else if(num.length==0){
        					alert("请选择接收单位");
        				}else{
        					ajaxSubmit($("#meetform"),function a(){
        						Ewin.confirm({ message: "发送成功，是否继续发送" }).on(function (e) {
     						       if (!e) {
     						    	   window.location.href=" <c:url value='/meet/meetIndex' />";
     						       }else{
     						       window.location.href=" <c:url value='/meet/meetInputIndex' />";
     						       }
     						 });
     					});
        				}
        			
        			}
        		});
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
    		$.post('<c:url value="/sys/getOrganizationTreeData" />',{},function(data){
    			ztreeObj = $.fn.zTree.init($("#ztreeBox"), setting, data.data);
    		},"json");
    		$.post('<c:url value="/sys/getOrganizationTreeData" />',{}, function(data) {
				ztreeObj = $.fn.zTree.init($("#mztreeBox"),settingtwo, data.data);}, "json");
			getMagroup();
    		$("#btn_add1").click(function(){
    			$("#fj").append('<div id="fjdiv_'+i+'"><div class="col-md-8"><input  type="file" name="upfile" /></div><div class="col-md-4"><div class="btn btn-default" onclick="del_1('+i+')"><b>删除</b></div></div></div>');
    					  i = i + 1;
    			});
    		
    	}); 
    	function getMagroup(){
    		$.ajax({
    			url : "<c:url value='/doc/getMagroupAll?select=all' />",
    			type : 'post',
    			dataType : 'json',
    			data:{
    				type:0
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
    		$("#cname").val("");
    		$("#managerformid").val("0");
    		$("#csort").val("");
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
    			url : "<c:url value='/doc/getMagroupAll?select=one'/>",
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
    				$("#cname").val(name);
    				$("#managerformid").val(id);
    				if(sort=="null"){
    				sort="";	
    				}
    				$("#csort").val(sort);
    				}
    				var ids=value.split(",");
    				var deps=result.data.departmentName.split(",");
    				for (i=0;i<ids.length;i++) 
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
