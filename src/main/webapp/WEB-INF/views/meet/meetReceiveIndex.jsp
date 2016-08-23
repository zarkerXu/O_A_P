<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<!-- The styles -->
<%@include file="../common/css.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common/meetReceive.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common/docAudit.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/zTreeStyle/zTreeStyle.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common/ztree.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.animation.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.widget.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.widget.ios.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.scroller.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.scroller.ios.css' />">

</head>

<body>

	<%@include file="../common/header.jsp"%>

	<%@include file="../common/nav.jsp"%>

	<div class="col-lg-10 index-iframe">
		<div class="docInput-top">
			<ol class="breadcrumb">
				<li><a href="<c:url value='/home/index' />"><i class="icon-home"></i> 首页</a></li>
				<li class="active">会议接收</li>
			</ol>
		</div>
		<!-- 页面顶部结束 -->

		<!-- 表单开始 -->
		<form id="selectForm" action="<c:url value='/meet/meetReceive' />"
			method="post">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label for="level" class="col-xs-5 control-label">等&nbsp;
								&nbsp; &nbsp; &nbsp;级：</label>
							<div class="col-xs-7">
								<select class="form-control" id="level" name="level">
								    <option value="">全部</option>
									<option value="1">特急</option>
									<option value="2">加急</option>
									<option value="3">平急</option>
									<option value="4">特提</option>
								</select>
							</div>
						</div>

					</div>
					<div class="col-md-4">

						<div class="form-group">
							<label for="docNo2" class="col-xs-5 control-label">发文编号：</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="docNo2" name="docNo">
							</div>
						</div>

					</div>
					<div class="col-md-4">

						<div class="form-group">
							<label for="docTitle2" class="col-xs-5 control-label">发文标题：</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="docTitle2" name="docTitle">
							</div>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top: 10px;">
					<div class="col-md-4">
						<div class="form-group">
							<label for="level" class="col-xs-5 control-label">承办单位：</label>
							<div class="col-xs-7">
								<select class="form-control" id="meetCompany" name="meetCompany">
								</select>
							</div>
						</div>

					</div>
					<div class="col-md-4">

						<div class="form-group">
							<label for="docNo2" class="col-xs-5 control-label">会议名称：</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="name" name="name">
							</div>
						</div>

					</div>
					<div class="col-md-4">

					</div>
				</div>
				<div class="row" style="margin-top: 10px; margin-bottom: 10px">
					<div class="col-md-4">

						<div class="form-group">
							<label for="startTime" class="col-xs-5 control-label">开始时间：</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="startTime" name=startTime>
							</div>
						</div>

					</div>
					<div class="col-md-4">

						<div class="form-group">
							<label for="endTime" class="col-xs-5 control-label">结束时间：</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="endTime" name="endTime">
							</div>
						</div>

					</div>
					<div class="col-md-4" >
						<div class="pull-right" style="margin-right: 15px;">
							<button type="button" class="btn btn-default" onclick="btnsub()">
								<strong>查 询</strong>
							</button>
						</div>
					</div>

				</div>

			</div>
		</form>
		<!-- 表单结束 -->

		<!-- 表格开始 -->
		<table class="table table-bordered table-hover change">
			
				<tr>
					<th>序号</th>
					<th>发布时间</th>
					<th>等级</th>
					<th>会议名称</th>
					<th>发文编号</th>
					<th>发文标题</th>
					<th>发文单位</th>
					<th>承办单位</th>
					<th>会议时间</th>
					<th>内容</th>
					<th>签收状态</th>
					<th>审批状态</th>
					<th>操作</th>	
				</tr>
			
<tbody id="tablebody" >
  	</tbody>
	</table>
	<nav class="center" id="bottomnav">
    <ul class="pagination">
     <li class="disabled"><a href="javascript:void(0);">&laquo;</a></li>
     <li class="active"><a href="javascript:void(0);">1</a></li>
     <li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>
     </ul>
    </nav>
 	<input type="hidden" value="" id="pages"/>
	</div>
	
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
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
               <div class="title" id="organizeDepartmentInfo">  
               
               </div>
               
               <div class="line">
                 <div class="block">
                 	<div class="first" > 
                 	会议时间：
                 	</div>
                 	<div class="second" id="meetTime">
                 	</div>
                 </div>
                 <div class="block">
                 	<div class="first"> 
                 	会议名称：
                 	</div>
                 	<div class="second" id="meetName">
                 	
                 	</div>
                 </div>
               </div>
               <div class="line">
                 <div class="block">
                 	<div class="first" > 
                 	联系人：
                 	</div>
                 	<div class="second" id="contactName"> 
                 	</div>
                 </div>
                 <div class="block">
                 	<div class="first"> 
                 	联系电话：
                 	</div>
                 	<div class="second" id="contactPhone">
                 	
                 	</div>
                 </div>
               </div>
               <div class="title_b" id="address">   	
               </div> 
               
               <div class="line">
                 <div class="block">
                 	<div class="first" > 
                 	发文编号：
                 	</div>
                 	<div class="second" id="docNo">
                 	</div>
                 </div>
                 <div class="block">
                 	<div class="first"> 
                 	等级：
                 	</div>
                 	<div class="second" id="doclevel">
                 	
                 	</div>
                 </div>
               </div>
               
               <div class="title" id="doctitle">
                     
               </div>
               <div class="title_b" id="docinfo">   	
               </div>    
               <div class="line">
               <div class="first"> 
                	   附件：
               </div>
               <div class="fjtable">
               <form action="" method="post" id="getfj"></form>
                 <table class="table table-bordered " id="attach">
                                     
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
<div class="modal fade " id="myModal2" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog docmodel">
      <div class="modal-content ">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               会议报名
            </h4>
         </div>
         <div class="main-body">
         <div class="modal-body">
                                          报名名单
            <table  class=" table table-hover noborder">
             <tr>
                  <th>姓名</th>
                  <th>职位</th>
                  <th>性别</th>
                  <th>联系方式</th>
                  <th>状态</th>
                  <th>审批状态</th>
                  <th>备注</th>
                  <th>审批反馈<th>
                  <th>操作</th>
             </tr>
             
             <tbody id="allnum" class="noborder">
             </tbody>
       
             </table>
             <div class="clear"></div>
             <div class="border"></div>
            
             
             <div class="clear"></div>
         </div>
         </div>
         <div class="modal-footer">
            <button id="adduser" type="button" class="btn btn-default leftbutton" data-toggle="modal" 
         data-target="#addModal" onclick="adduser();">添加
            </button>
            <button id="getthis" type="button" class="btn btn-success" onclick="getthis()">提交审批
            </button>
         </div>
      </div>
</div>
</div>
<div class="modal fade " id="addModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog docmodel">
     <form id="adduserform" action="<c:url value='/meet/updateMEntry' />" method="post">
     <input type="hidden" name="editType" id="editType" />
     <input type="hidden" name="id" id="emid" />
     <input type="hidden" name="pid" id="pid" />
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="addLabel">
               		人员添加
            </h4>
         </div>
         <div class="modal-body">
            <div class="addblockone">
            </div>
          
            <div class="addblock">
            	<div class="nav">
                <div class="text">姓名</div>
                </div>
                <div class="nav">
                  <div class="inputline">
                  <input type="text" class="form-control" placeholder="姓名" id="getname" name="name"/>
                  </div>
                </div>
            </div>
            
            <div class="addblock">
            	<div class="nav">
            	<div class="text">职位</div>
            	</div>
                <div class="nav">
                <div class="inputline">
                  <input type="text" class="form-control" placeholder="职位" id="getposition" name="post"/>
                  </div>
                </div>
            </div>
            <div class="addblock">
            	<div class="nav">
            	<div class="text">联系方式</div>
            	</div>
                <div class="nav">
                <div class="inputline">
                  <input type="text" class="form-control" placeholder="联系方式" id="getphonenumber" name="phone"/>
                  </div>
                </div>
            </div>
            <div class="addblockone">
                <div class="nav"><div class="text">性别</div></div>
                <div class="nav"><div class="inputline">
                  	<select class="form-control" id="getsex" name="sex">
         				<option value="0">男</option>
         				<option value="1">女</option>
     				</select>
                  </div></div>
            </div>
            <div class="addblock">
                <div class="nav">
            	<div class="text">状态</div>
            	</div>
                <div class="nav">
                  <div class="inputline">
                  	<select class="form-control" id="getstatus" name="type">
         				<option value="0">参会</option>
         				<option value="1">听会</option>
        			    <option value="2">请假</option> 
     				</select>
                  </div>
                </div>
            </div>
           
            <div class="clear"></div>
            备注：
           <textarea class="form-control textareatop "  id="meetremark" name="remark"></textarea>
           审批反馈：
           <textarea class="form-control textareatop "  id="passremark" name="passRemark" disabled="disabled"></textarea>
         </div>
         
         <div class="modal-footer">
            <button type="button" class="btn btn-danger leftbutton" data-dismiss="modal">取消
            </button>
            <button type="submit" class="btn btn-success" >确定
            </button>
         </div>
      </div>
       </form>
</div>
</div>
<div class="modal fade" id="relaydoc" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog docmodel">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
              	 会议转发
            </h4>
         </div>
         
         <div class="modal-body">
         <form id="zfForm" action="<c:url value='/others/Relay' />"
			method="post">
         <div class="form-group">
			<label for="name">转发意见:</label> 
           <textarea class="form-control textareatop "  id="relayRemark" name="relayRemark"  style="width:100%;"></textarea>
            </div>
          	   <div class="form-group">
					<div class="managerinfoblock">
							<div class="form-group">
								<label for="name">转发的单位:</label>
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
           <input name="type" type="hidden" value="2">
           <input id="zfid" name="id" type="hidden">
           </form>
         </div>
       
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="zfsumb();">提交</button>
         </div>
      </div><!-- /.modal-content -->
</div>
</div>

	<%@include file="../common/js.jsp"%>
	<script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.core.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.widget.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.scroller.js' />"></script>
    

    <script src="<c:url value='/resources/js/tool/mobiscroll.util.datetime.js' />"></script>
    <script src="<c:url value='/resources/js/tool/mobiscroll.datetimebase.js' />"></script>

    <script src="<c:url value='/resources/js/tool/mobiscroll.widget.ios.js' />"></script>

    <script src="<c:url value='/resources/js/tool/mobiscroll.i18n.zh.js' />"></script>
     <script type="text/javascript"
		src='<c:url value="/resources/js/common/ztree.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/tool/jquery.ztree.core.js"/>'></script>
    <script type="text/javascript">
        $(function () {
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
            $('#startTime').mobiscroll(opt);
            $('#endTime').mobiscroll(opt);
        });
    </script>
<script type="text/javascript">
var pid="";
$(document).ready(function(){
	setOrganization();
	$("#meetReceiveIndex").css({"background":"#c91306","color":"#ffffff"});
	ajaxSubmit($("#selectForm"),setTable);
	$("#adduserform").validate({
		errorPlacement : function(error, element) {
			error.replaceAll($("#" + $(element).attr("name") + "-error"));
		},
		rules : {
			name : {
				required : true
			},
			post:{
				required : true
			}
		},
		messages : {
			name : {
				required : true
			},
			position:{
				required : true
			}
		},
		submitHandler : function() {
			ajaxSubmit($("#adduserform"),refresh);
			$("#addModal").modal('hide');
		}
	});
});
jQuery.fn.limit=function(){  
    var self = $("[limit]");  
    self.each(function(){  
        var objString = $(this).text();  
        var objLength = $(this).text().length;  
        var num = $(this).attr("limit");  
        if(objLength > num){  
            $(this).attr("title",objString);  
            objString = $(this).text(objString.substring(0,num) + "...");  
        }  
    })  
}  
function setTable(data){
    var page = data.page;
    var size = data.size;   
    var totalPage;
    if(data.totalPage%size==0)
    	totalPage=data.totalPage/size;
    else{
    	totalPage =  parseInt(data.totalPage/size +1);
    }
	var html="";
	var i=1;
	if(page>1){
		i=(page-1)*size+1;
	}
	 $.each(data.pageObject,function() {
		 var datetime=times(this.createTime);
		 var meettime=times(this.meetTime);
		 html+="<tr><td>&nbsp;"
		 +i+"</td><td>"+datetime+"</td><td id='lev'>"
		 +(this.level==1?"特急":this.level==2?"加急":this.level==3?"平急":this.level==4?"特提":"")+"</td><td>"+this.name+"</td><td>"+this.docNo+"</td><td>"+this.docTitle+"</td><td>"
		 +this.sendDepartmentInfo+"</td><td>"
		 +this.meetCompanyName+"<td>"+meettime+"</td><td><button type='button' class='btn btn-link' data-toggle='modal' data-target='#myModal' onclick=\"showinfo('"+this.id
				 +"','"+this.tid
				 +"','"+this.signStatus
				 +"')\">查看</button></td><td class='tdmiddle'>"+(this.signStatus==0?"已签收":"未签收")+"</td><td class='tdmiddle'>"+(this.passStatus==0?"已审批":this.passStatus==1?"审批未通过":this.passStatus==2?"待审批":this.passStatus==3?"未报名":"")+
				 "</td><td><center>";
		 if(this.personnel==null){
		 if(this.passStatus!=0){
			 html+='<button type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal2" onclick="nownum(\''+this.tid+'\',false)">报名</button>';
		 }else{
			 html+='<button type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal2" onclick="nownum(\''+this.tid+'\',true)">查看报名</button>';
		 }
		 }
	     html+="<br/></center><center><a href='#' onclick='relay(\""+this.id+"\")'>转发</a></center>";
					 html+= "</td></tr>";
	 	 i++;
	 });
	 $("#tablebody").html(html);	 
	 lilist(page, totalPage,size);   
}
function next(url){
	$("#selectForm").attr("action",url);
	ajaxSubmit($("#selectForm"),setTable);
}

function btnsub(){
	var url="<c:url value='/meet/meetReceive'/>";
	$("#selectForm").attr("action",url );
	ajaxSubmit($("#selectForm"),setTable);
}
function geturl(i,size){
	return  "<c:url value='/meet/meetReceive?page=" + i + "&size=" + size + "'/>";
}
function showinfo(id,tid,signStatus){
	$.ajax({
		url : "<c:url value='/meet/getMeet' />",
		type : 'post',
		dataType : 'json',
		data : {"id" : id},
		success : function(result) {
			var datetime=times(result.data.meetTime);
			$("#department").html("<b>发文单位：</b>"+result.data.sendDepartmentInfo);
			$("#organizeDepartmentInfo").html("<b>承办单位：</b>&nbsp;"+result.data.meetCompanyName);
			$("#docNo").html(result.data.docNo);
			$("#doclevel").html(result.data.level==1?"特急":result.data.level==2?"加急":result.data.level==3?"平急":result.data.level==4?"特提":"");
			$("#meetTime").html(datetime);
			$("#meetName").html(result.data.name);
			$("#contactName").html(result.data.contactName);
			$("#contactPhone").html(result.data.contactPhone);
			$("#addr").html(result.data.addr);
			$("#doctitle").html("<b>发文标题：</b>&nbsp;"+result.data.docTitle);
			$("#docinfo").html("<b>发文摘要：</b>&nbsp;"+result.data.docSummary);
			$.ajax({
				url : "<c:url value='/meet/getAttach' />",
				type : 'post',
				dataType : 'json',
				data : {"pid" : id},
				success : function(result) {
					var html="";
					if(result.data==""){
						sign(signStatus,tid);
					}
					 $.each(result.data,function() {
						 var fileurl="<c:url value='/attachment/download?id=" + this.id + "'/>";
						 html+="<tr><td>"+this.fileName+"</td><td><a onclick=\"sign('"+signStatus+"','"+tid+"')\" target=\"_blank\" href='"+fileurl+"'>查看</a></td></tr>";
					 });
					 $("#attach").html(html);
				}
			});
		}
	});	
} 

function sign(signStatus,tid){
	if(signStatus==1){
		$.ajax({
			url : "<c:url value='/meet/meetSign' />",
			type : 'post',
			dataType : 'json',
			data : {"tid" : tid},
			success : function(result) {
			
				ajaxSubmit($("#selectForm"),setTable);
			}
		});
	}
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
					html+="<option value='"+this.id+"'>"+this.name+"</option>";
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
			$("#meetCompany").html("<option value=''>全部</option>"+option);
		}
	});
}


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
					var html = insertSelectedDwzf(treeNode.id, treeNode.name);
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
var ztreeObj;
$(document).ready(function() {
					$.post('<c:url value="/others/getOrganizationTreeData" />',{}, function(data) {
						ztreeObj = $.fn.zTree.init($("#mztreeBox"),settingtwo, data.data);}, "json");				
	});
	
function relay(id){
			$("#zfid").val(id);
			$("#relaydoc").modal("show");
		
}
function zfsumb(){
	ajaxSubmit($("#zfForm"),function(){
		$("#relaydoc").modal("hide");
		$("#mjsdwText").html("");
	});
}


function getthis(){
	var b=false;
	$(".sp").each(function(){
		if($(this).text()=="审批未通过"){
			b=true;
		}
	});
	if(b){
		return;
	}
	$("#myModal2").modal("hide");
	$("#allnum").html("");
	$.ajax({
		url : "<c:url value='/meet/updateTaskPassStatus' />",
		type : 'post',
		dataType : 'json',
		data : {"id": pid,"status":2},
		success : function(result) {
			if(result.code==1){
				alert("添加失败");
				}
				ajaxSubmit($("#selectForm"),setTable);
			}
		});
}
function nownum(id,b){
	clean();
	pid=id;
	$("#allnum").html("");
	$.ajax({
		url : "<c:url value='/meet/getMEntryByPid' />",
		type : 'post',
		dataType : 'json',
		data : {"pid": id},
		success : function(result) {
			$.each(result.data,function() {
				$("#allnum").append("<tr class='listview'><td>"+
						this.name+"</td><td>"+
						this.post+"</td><td>"+
						(this.sex==0?"男":"女") +"</td><td>"+
						this.phone+"</td><td>"+
						(this.type==0?"参会":this.type==1?"听会":this.type==2?"请假":"") +"</td><td class='sp'>"+
						(this.passStatus==0?"审批通过":this.passStatus==1?"审批未通过":this.passStatus==3?"未审批":"")+"</td><td limit='8'>"
						+(this.remark==null?"":this.remark)+"</td><td limit='8'>"
						+(this.passRemark==null?"":this.passRemark)+"</td><td></td><td>"
						+ (b?"":(this.passStatus==0?"":"<button type='button' class='btn btn-link' data-toggle='modal' data-target='#addModal' onclick=\"updateuser('"+this.id+"')\">修改</button><button type='button' class='btn btn-link' onclick=\"deluser('"+this.id+"')\">删除</button>"))
						+"</td></tr>");
			});
			 $("[limit]").limit();
			 if(b){
				 $("#adduser").hide();
				 $("#getthis").hide();
			 }
		}
	});
}
function adduser(){
	clean();
	$("#addLabel").val("人员添加");
	$("#editType").val("add");
	$("#pid").val(pid);
}

function deluser(id){
	Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
	       if (!e) {
	         return;
	       }
	       $.ajax({
				url : "<c:url value='/meet/delMEntry' />",
				type : 'post',
				dataType : 'json',
				data : {
					"id" : id
					},
				success : function(result) {
					if(result.code==0){
						successAlert("删除成功");
						refresh();
					}
				}
				});
	 });
}

function updateuser(id){
	clean();
	$("#addLabel").val("人员修改");
	$("#editType").val("edit");
	$.ajax({
		url : "<c:url value='/meet/getMEntry' />",
		type : 'post',
		dataType : 'json',
		data : {"id": id},
		success : function(result) {
			$("#emid").val(id);
			$("#getname").val(result.data.name);
			$("#getposition").val(result.data.post);
			$("#getphonenumber").val(result.data.phone);
			$("#getsex").val(result.data.sex);
			$("#getstatus").val(result.data.type);
			$("#meetremark").val(result.data.remark);
			$("#passremark").val(result.data.passRemark);
		}
	});
}
function refresh(){
	clean();
	nownum(pid,false);
}

function clean(){
	$("#adduserform input").each(function(index){
		$(this).val("");
		$(this).attr("disabled",false);
	});
	$("#getsex").val(0);
	$("#getsex").attr("disabled",false);
	$("#getstatus").val(0);
	$("#getstatus").attr("disabled",false);
	$("#meetremark").val("");
	$("#meetremark").attr("disabled",false);
	$("#passremark").val("");
	$("#adduser").show();
	$("#getthis").show();
}

	</script>
</body>
</html>
