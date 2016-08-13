<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<%@include file="common/css.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common/docAudit.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common/scroll.css'/>">
<!-- The styles -->
<style type="text/css">
</style>

</head>

<body>
	<%@include file="common/header.jsp"%>

	<%@include file="common/nav.jsp"%>
	
	<div class="col-lg-10 index-iframe" >
	<div class="index_1-top">
		<ol class="breadcrumb">
		<a href="#">
		  <li class="active"><i class="icon-home"></i> 首页</li>
		</a>
		</ol>
	</div>
		<div>
			<div class="col-md-8">
			<a href="<c:url value='/doc/docInputIndex' />">
				<div class="col-md-4">
					<div class="thumbnail">
						<i class="icon-edit"></i>
						<div class="caption">
							<h3 class="text-center">公文发布</h3>
						</div>
					</div>
				</div>
				</a>
				<a href="<c:url value='/doc/docReceiveIndex' />">
				<div class="col-md-4">
					<div class="thumbnail">
						<i class="icon-download-alt"></i>
						<div class="caption">
							<h3 class="text-center">公文接收</h3>
						</div>
					</div>
				</div>
				</a>
				<a href="<c:url value='/doc/docIndex' />">
				<div class="col-md-4">
					<div class="thumbnail">
						<i class="icon-file-alt"></i>
						<div class="caption">
							<h3 class="text-center">已发布公文</h3>
						</div>
					</div>
				</div>
				</a>
			</div>
			<!-- 页面右侧开始 -->
			<div class="col-md-4">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<i class="icon-tags"></i> 待办事宜
					</div>
					<div class="panel-body">
						<p>
							<i class="icon-double-angle-right"></i> <a href="<c:url value='/doc/docReceiveIndex' />"
								target="iframe">您现在有<span id="docNum">0</span>封新发布公文尚未签收，请注意签收！</a>
						</p>
						<hr />
						<p>
							<i class="icon-double-angle-right"></i> <a
								href="<c:url value='/meet/meetReceiveIndex' />" target="iframe">您现在有<span id="meetNum">0</span>个新发布会议尚未签收，请注意签收！</a>
						</p>
						<hr />
						<p>
							<i class="icon-double-angle-right"></i> <a
								href="<c:url value='/others/ApproveIndex' />" target="iframe">您现在有<span id="docApproveNum">0</span>个待审批的公文，<span id="meetApproveNum">0</span>个待审批的会议，请注意签收！</a>
						</p>
						
					</div>
				</div>
			</div>
			
		</div>
		<!-- 页面右侧结束 -->
		<div>
			<div class="col-md-8">
			    <a href="<c:url value='/meet/meetInputIndex' />">
				<div class="col-md-4">
					<div class="thumbnail">
						<i class="icon-group"></i>
						<div class="caption">
							<h3 class="text-center">会议发布</h3>
						</div>
					</div>
				</div>
				</a>
				<a href="<c:url value='/meet/meetReceiveIndex' />">
				<div class="col-md-4">
					<div class="thumbnail">
					    <i class="icon-download"></i>
						<div class="caption">
							<h3 class="text-center">会议接收</h3>
						</div>
					</div>
				</div>
				</a>
				<a href="<c:url value='/meet/meetIndex' />">
				<div class="col-md-4">
					<div class="thumbnail">
					    <i class="icon-copy"></i>
						<div class="caption">
							<h3 class="text-center">已发布会议</h3>
						</div>
					</div>
				</div>
				</a>
			</div>
		</div>
		<!--  -->
		<div class="col-md-4">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<i class="icon-tags"></i> <a
								href="<c:url value='/others/noticeAuditIndex' />" target="iframe">公告	<span id="noticenum"></span></a>
					</div>
					<div id="scrolling_v">
					<div id="scrollnews_v">
					<div id="scrollnews_con_v">

</div>
</div>	
</div>

				</div>
			</div>
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
<div>
</div>
	<%@include file="common/footer.jsp"%>
	<%@include file="common/js.jsp"%>
	<script type="text/javascript" src="<c:url value='/resources/js/common/scroll.js' />"></script>

<script type="text/javascript">

$(document).ready(function(){
	$("#home").css({"background":"#c91306","color":"#ffffff"});
	var html="";
	$.ajax({
		url : "<c:url value='/home/getIndexNum' />",
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code==0){
				$("#docNum").text(result.data.docNum);
				$("#meetNum").text(result.data.meetNum);
				$("#docApproveNum").text(result.data.docApproveNum);
				$("#meetApproveNum").text(result.data.meetApproveNum);
			}
		}
	});
	$.ajax({
		url : "<c:url value='/others/HomegetNotice' />",
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var i=0;
			$.each(result.data,function(){
				if(i%3==0){
					if(i==0){
					html+="<ul>";
					}else{
						html+="</ul><ul>";
					}					
				}
				html+="<li><i class='icon-double-angle-right'></i><a href='#'  onclick='showNotice(\""+this.id+"\");' >"+"&nbsp;"+this.title+"</a></li>";
				i++;
			});
			
			html.substring(0,html.length-4);
			
			$("#scrollnews_con_v").html(html);
			$("#noticenum").html("（您有"+i+"个公告）");
			if(i==0){
				return;
			}
			YScroll({
				area: 'scrollnews_v',
				msg: 'scrollnews_con_v',
				items: 'ul',
				speed: 50
			});	
		}
	});
	
});
function showNotice(id){
	$.ajax({
		url : "<c:url value='/others/getNotice'/>",
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
			$("#show_department").html("<b>发送单位：</b>&nbsp;"+result.data.departmentName);
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

</script>
</body>
</html>
