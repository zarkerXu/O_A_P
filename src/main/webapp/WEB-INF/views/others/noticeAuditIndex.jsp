<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<%@include file="../common/css.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common/docAudit.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.animation.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.widget.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.widget.ios.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.scroller.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tool/mobiscroll.scroller.ios.css' />">
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
		  <li class="active"> 公告</li>
		</ol>
	</div>
	<!-- 页面顶部结束 -->

	<!-- 表格开始 -->
<form id="dotest" action="<c:url value='/others/getOwnNoticeList' />"
			method="post">
			<div class="container-fluid">
			
			<div class="row" >
					
					<div class="col-md-4">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="docTitle2" class="col-xs-4 control-label">公告标题：</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="docTitle2" name="title" value="">
							</div>
						</div>
						</div>
						
					</div>
					
				</div>
			
				<div class="row" >
					<div class="col-md-4">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="startTime7" class="col-xs-4 control-label">开始时间：</label>
							<div class="col-xs-6">
								<input type="text" class="form-control" id="startTime" name="startTime">
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-4">
						<div class="form-horizontal">
						<div class="form-group">
							<label for="endTime7" class="col-xs-6 control-label">结束时间：</label>
							<div class="col-xs-6">
								<input type="text" class="form-control" id="endTime" name="endTime">
							</div>
						</div>
						</div>
					</div>
					<div class="col-md-3" >
					<div class="form-horizontal">
						<div class="pull-right" >
							<button type="button" class="btn btn-default" onclick="btnsub()">
								<strong>查 询</strong>
							</button>
						</div>
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
					<th>标题</th>
					<th>详细信息</th>
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
	<%@include file="../common/js.jsp"%>
		<script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.core.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.widget.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.scroller.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.util.datetime.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.datetimebase.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.widget.ios.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/tool/mobiscroll.i18n.zh.js' />"></script>
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
    $(document).ready(function(){
    	$("#noticeAuditIndex").css({"background":"#c91306","color":"#ffffff"});
ajaxSubmit($("#dotest"),setTable);
});
function setTable(data){
    var page = data.page;
    var size = data.size;
    var totalPage =  parseInt(data.totalPage/size +1);
    if(data.totalPage%size==0)
    	totalPage=data.totalPage/size;
	var html="";
	var i=1;
	if(page>1){
		i=(page-1)*size+1;
	}
	 $.each(data.pageObject,function() {
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
		 var datetime=date.getFullYear()+"-"+month+"-"+date.getDate()+" "+date.getHours()+":"+minutes+":"+seconds;  
		 html+="<tr><td>"
		 +i+"</td><td>"+datetime+"</td><td>"+this.title+"</td><td><a href='#' onclick='showNotice(\""+this.id+"\");' > 查看</a></td></tr>";
	 	 i++;
	 });
	 $("#tablebody").html(html);	 
	 lilist(page, totalPage,size);   
}
function geturl(i,size){
	return  "<c:url value='/others/getOwnNoticeList?page=" + i + "&size=" + size + "'/>";
}
function btnsub(){
	ajaxSubmit($("#dotest"),setTable);
}
function next(url){
	$("#dotest").attr("action",url);
	ajaxSubmit($("#dotest"),setTable);
}
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
			$("#show_department").html("<b>发布单位：</b>&nbsp;"+result.data.departmentName);
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