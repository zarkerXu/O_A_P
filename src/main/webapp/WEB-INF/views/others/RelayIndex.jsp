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
				<li class="active">我的转发</li>
			</ol>
		</div>
		<!-- 页面顶部结束 -->

		<!-- 表单开始 -->
		<form id="selectForm" action="<c:url value='/others/findRelay' />"
			method="post">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label for="level" class="col-xs-6 control-label">等&nbsp;
								&nbsp; &nbsp; &nbsp;级：</label>
							<div class="col-xs-6">
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
							<label for="docNo2" class="col-xs-6 control-label">发文编号：</label>
							<div class="col-xs-6">
								<input type="text" class="form-control" id="docNo2" name="docNo">
							</div>
						</div>

					</div>
					<div class="col-md-4">

						<div class="form-group">
							<label for="docTitle2" class="col-xs-6 control-label">发文标题：</label>
							<div class="col-xs-6">
								<input type="text" class="form-control" id="docTitle2" name="docTitle">
							</div>
						</div>
					</div>
				</div>
				
				</div>
				<div class="container-fluid" style="margin-top: 5px;">
				<div class="row" >
				<div class="col-md-4">

						<div class="form-group">
							<label for="docNo2" class="col-xs-6 control-label">会议名称：</label>
							<div class="col-xs-6">
								<input type="text" class="form-control" id="meetname" name="name">
							</div>
						</div>

					</div>
					
					<div class="col-md-4">

						<div class="form-group">
							<label for="startTime" class="col-xs-6 control-label">开始时间：</label>
							<div class="col-xs-6">
								<input type="text" class="form-control" id="startTime" name=startTime>
							</div>
						</div>

					</div>
					<div class="col-md-4">

						<div class="form-group">
							<label for="endTime" class="col-xs-6 control-label">结束时间：</label>
							<div class="col-xs-6">
								<input type="text" class="form-control" id="endTime" name="endTime">
							</div>
						</div>


				</div>

			</div>
			</div>
			<div class="container-fluid" style="margin-top: 5px;margin-bottom: 5px;float:right;">
			<div class="col-md-4" >
						<div class="pull-right" style="margin-right:-30px;">
							<button type="button" class="btn btn-default" onclick="btnsub()">
								<strong>查 询</strong>
							</button>
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
			<th>审批状态</th>
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
            <div class="title" id="getdepartmentInfo">
            </div>
               <div class="title" id="senddepartment">  
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
                 <table class="table table-bordered " id="attach">
                                     
                 </table>
               </div>   
               </div>
               <hr/>
               <div class="title_b" id="mzfremark">
               
               </div> 
               <div class="title_b" id="msendDepart">
               
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
      
</div>
</div> 

<div class="modal fade" id="checksign" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog docmodel">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" >
      		       审批状态 
            </h4>
         </div>
         <div class="modal-body">
             <div class="docmain">
             <form method="post" action="" id="checkinfo">
             </form>
             <table class="table table-bordered table-hover change">		
				<tr>
				    <th></th>
					<th>部门</th>
					<th>姓名</th>
					<th>审批状况</th>
					<th>审批反馈</th>
				</tr>
			
			<tbody id="checkinfotable" style="border:0px solid #2d2d2d;">
			
  			</tbody>
			</table>
	      
             </div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
         </div>
      </div>
</div>
</div>

<!-- 显示会议详情 -->
	<div class="modal fade" id="meetModal" tabindex="-1" role="dialog" 
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
            <div class="title" id="mdepartment">  
               
               </div>
            <div class="title" id="msenddepartment">  
               
               </div>
               <div class="title" id="morganizeDepartmentInfo">  
               
               </div>
               
               <div class="line">
                 <div class="block">
                 	<div class="first" > 
                 	会议时间：
                 	</div>
                 	<div class="second" id="mmeetTime">
                 	</div>
                 </div>
                 <div class="block">
                 	<div class="first"> 
                 	会议名称：
                 	</div>
                 	<div class="second" id="mmeetName">
                 	
                 	</div>
                 </div>
               </div>
               
               <div class="line">
                 <div class="block">
                 	<div class="first" > 
                 	联系人：
                 	</div>
                 	<div class="second" id="mcontactName"> 
                 	</div>
                 </div>
                 <div class="block">
                 	<div class="first"> 
                 	联系电话：
                 	</div>
                 	<div class="second" id="mcontentphone">
                 	
                 	</div>
                 </div>
               </div>
               <div class="title_b" id="maddress">   	
               </div> 
               
               <div class="line">
                 <div class="block">
                 	<div class="first" > 
                 	发文编号：
                 	</div>
                 	<div class="second" id="mdocNo">
                 	</div>
                 </div>
                 <div class="block">
                 	<div class="first"> 
                 	等级：
                 	</div>
                 	<div class="second" id="mdoclevel">
                 	
                 	</div>
                 </div>
               </div>
               
               <div class="title" id="mdoctitle">
                     
               </div>
               <div class="title_b" id="mdocinfo">   	
               </div>      
               <div class="line">
               <div class="first"> 
                	   附件：
               </div>
               <div class="fjtable">
               <form action="" method="post" id="getfj"></form>
                 <table class="table table-bordered " id="mattach">
                                     
                 </table>
               </div>   
               </div>
               <hr/>
               <div class="title_b" id="zfremark">
               
               </div> 
               <div class="title_b" id="sendDepart">
               
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
    

    <script src="<c:url value='/resources/js/tool/mobiscroll.util.datetime.js' />"></script>
    <script src="<c:url value='/resources/js/tool/mobiscroll.datetimebase.js' />"></script>

    <script src="<c:url value='/resources/js/tool/mobiscroll.widget.ios.js' />"></script>

    <script src="<c:url value='/resources/js/tool/mobiscroll.i18n.zh.js' />"></script>
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
	$("#RelayIndex").css({"background":"#c91306","color":"#ffffff"});
	ajaxSubmit($("#selectForm"),setTable);
});

function setTable(data){
    var page = data.page;
    var size = data.size;   
    var totalPage =  parseInt(data.totalPage/size +1);
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
		 +(this.level==1?"特急":this.level==2?"加急":this.level==3?"平急":"特提")+"</td><td>"+(this.name==null?'':this.name)+"</td>";
		 html+="<td>"+this.docNo+"</td><td>"+this.docTitle+"</td><td>"
		 +this.oldSendDepartment+"</td><td>"+(this.meetCompany==null?'':this.meetCompany)+"</td><td>"+meettime+"</td>";
		 if(this.type==1){
		 html+="<td><button type='button' class='btn btn-link' onclick=\"showdoc('"+this.id
				 +"','"+this.signStatus
				 +"','"+this.sendDepartmentInfo
				 +"','"+this.oldSendDepartment
				 +"')\">查看</button></td>";
		 }else if(this.type==2){
			 html+="<td><button type='button' class='btn btn-link'  data-toggle='modal' data-target='#meetModal'onclick=\"showmeetinfo('"+this.id
			 +"','"+this.oldid
			 +"','"+this.sendDepartmentInfo
			 +"','"+this.oldSendDepartment
			 +"')\">查看</button></td>"; 
		 }
				html+="<td class='tdmiddle'><a data-toggle='modal' data-target='#checksign' href='#' style='color:blue' onclick=\"showcheckinfo('"+this.id
						+"','"+this.type+"')\">"+this.signUid+"/"+this.signNum+"</a></td></tr>";
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
	ajaxSubmit($("#selectForm"),setTable);
}
function geturl(i,size){
		return  "<c:url value='/others/findRelay?page=" + i + "&size=" + size + "'/>";
}

function showdoc(id,signStatus,senddepartment,oldsendDepartmentInfo){
	$.ajax({
		url : "<c:url value='/doc/getDoc' />",
		type : 'post',
		dataType : 'json',
		data : {"id" : id},
		success : function(result) {
			showinfo(result.data.docNo,result.data.level,result.data.docTitle,result.data.docSummary,result.data.oldid,signStatus,senddepartment,result.data.departmentInfo,result.data.relayRemark,oldsendDepartmentInfo);
		}
		});
}

function showinfo(docNo,level,docTitle,docSummary,id,signStatus,senddepartment,departmentInfo,relayRemark,oldsendDepartmentInfo){
	$("#getdepartmentInfo").html("<b>接收单位：</b>&nbsp;"+departmentInfo);
    $("#senddepartment").html("<b>发文单位：</b>&nbsp;"+oldsendDepartmentInfo);
	$("#docNo").html(docNo);
	$("#doclevel").html(level==1?"特急":level==2?"加急":level==3?"平急":"特提");
	$("#doctitle").html("<b>文件标题：</b>&nbsp;"+docTitle);
	$("#docinfo").html("<b>发文摘要：</b>&nbsp;"+docSummary);
	$("#mzfremark").html("<b>转发意见：</b>&nbsp;"+(relayRemark==null?"":relayRemark));
	$("#msendDepart").html("<b>转发单位：</b>&nbsp;"+(senddepartment==null?"":senddepartment));
	$.ajax({
		url : "<c:url value='/doc/getAttach' />",
		type : 'post',
		dataType : 'json',
		data : {"pid" : id},
		success : function(result) {
			var html="";
			 $.each(result.data,function() {
				 var fileurl="<c:url value='/attachment/download?id=" + this.id + "'/>";
				 html+="<tr><td>"+this.fileName+"</td><td><a  target=\"_blank\" href='"+fileurl+"'>查看</a></td></tr>";
			 });
			 $("#attach").html(html);
			 $("#myModal").modal("show");
		}
	});
	
}

function showcheckinfo(id,type){
	
	var url="<c:url value='/others/findapproved?did="+id+"&type="+type+"'/>";
	$("#checkinfo").attr("action",url );
	$("#checkinfotable").html("");
	ajaxSubmit($("#checkinfo"),setcheckinfo);
}
function setcheckinfo(data){
	var html="";
	var i=1;
	 $.each(data,function() {
		
       var status=(this.remark==0?"已审批":"待审批");
		 html+="<tr><td>"+i+"</td><td>"+this.organame+"</td><td>"+(this.uname==null?"":this.uname)+"</td><td>"+status+"</td><td><a  class='csshiden' style='text-decoration: none;' href='#' title='"+(this.passRemark==null?"":this.passRemark)+"'>"+(this.passRemark==null?"":this.passRemark)+"</a></td></tr>";
	   i++;
	 });
	 $("#checkinfotable").html(html);
}
function showmeetinfo(id,oldid,senddepartment,oldsendDepartmentInfo){
	$.ajax({
		url : "<c:url value='/meet/getMeet' />",
		type : 'post',
		dataType : 'json',
		data : {"id" : id},
		success : function(result) {
			var datetime=times(result.data.meetTime);
			$("#msenddepartment").html("<b>发文单位：</b>&nbsp;"+oldsendDepartmentInfo);
			$("#mdepartment").html("<b>接收单位：</b>&nbsp;"+result.data.departmentInfo);
			$("#morganizeDepartmentInfo").html("<b>承办单位：</b>&nbsp;"+result.data.meetCompanyName);
			$("#mdocNo").html(result.data.docNo);
			$("#mdoclevel").html(result.data.level==1?"特急":result.data.level==2?"加急":result.data.level==3?"平急":result.data.level==4?"特提":"");
			$("#mmeetTime").html(datetime);
			$("#mmeetName").html(result.data.name);
			$("#mcontactName").html(result.data.contactName);
			$("#mcontentphone").html(result.data.contactPhone);
			$("#maddr").html(result.data.addr);
			$("#mdoctitle").html("<b>发文标题：</b>&nbsp;"+result.data.docTitle);
			$("#mdocinfo").html("<b>发文摘要：</b>&nbsp;"+result.data.docSummary);
			$("#zfremark").html("<b>转发意见：</b>&nbsp;"+(result.data.relayRemark==null?"":result.data.relayRemark));
			$("#sendDepart").html("<b>转发单位：</b>&nbsp;"+(senddepartment==null?"":senddepartment));
			$.ajax({
				url : "<c:url value='/meet/getAttach' />",
				type : 'post',
				dataType : 'json',
				data : {"pid" : oldid},
				success : function(result) {
					var html="";
					 $.each(result.data,function() {
						 var fileurl="<c:url value='/attachment/download?id=" + this.id + "'/>";
						 html+="<tr><td>"+this.fileName+"</td><td><a  target=\"_blank\" href='"+fileurl+"'>查看</a></td></tr>";
					 });
					 $("#mattach").html(html);
				}
			});
		}
	});	
} 
	</script>
</body>
</html>
