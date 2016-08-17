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
		  <li class="active"> 会议审计</li>
		</ol>
	</div>
	<!-- 页面顶部结束 -->

	<!-- 表单开始 -->
	<form id="doselect" action="<c:url value='/meet/meetSelect' />" method="post">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4">
  			<div class="form-horizontal">
				  <div class="form-group">
				    <label for="docCompany7" class="col-xs-6 control-label">等    &nbsp; &nbsp; &nbsp; &nbsp;级：</label>
				    <div class="col-xs-6">
				    	<select class="form-control" id="level1" name="level">
				    	 		<option value="">全部</option>
			  				    <option value="1">特急</option>
								<option value="2">加急</option>
								<option value="3">平急</option>
								<option value="4">特提</option>
			  			</select>
				    </div>
				  </div>
				</div>
  		</div>
  		<div class="col-md-4">
				<div class="form-horizontal">
				  <div class="form-group">
				    <label for="docNumber7" class="col-xs-6 control-label">发文编号：</label>
							<div class="col-xs-6">
				    		<input type="text" class="form-control" id="docNumber7" name="docNo">
				    	</div>
				  </div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-horizontal">
				  <div class="form-group">
				    <label for="docTitle7" class="col-xs-6 control-label">文件标题：</label>
							<div class="col-xs-6">
				    		<input type="text" class="form-control" id="docTitle7" name="docTitle">
				    	</div>
				  </div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-horizontal">
				  <div class="form-group">
				    <label for="startTime7" class="col-xs-6 control-label">开始时间：</label>
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
			<div class="col-md-4">
				<div class="pull-right">
					<button type="button" class="btn btn-default" onclick="btnsub()"><strong>查 询</strong></button>
					<button type="button" class="btn btn-default" onclick="del()"><strong>删 除</strong></button>
				</div>
			</div>
		</div>
	</div>
	</form>
		<!-- 表单结束 -->
		<table class="table table-bordered table-hover change">
			
				<tr>
					<th><input type="checkbox" onClick="allSelect()">序号</th>
					<th>发布时间</th>
					<th>等级</th>
					<th>会议名称</th>
					<th>发文编号</th>
					<th>发文标题</th>
					<th>承办单位</th>
					<th>会议时间</th>
					<th>内容</th>
					<th>已签收/应签收</th>
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
		<!-- 表格开始 -->
  <%@include file="../common/footer.jsp"%>
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
                 	<div class="second" id="contentname"> 
                 	</div>
                 </div>
                 <div class="block">
                 	<div class="first"> 
                 	联系电话：
                 	</div>
                 	<div class="second" id="contentphone">
                 	
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
            <h4 class="modal-title" id="myModalLabel">
             会议签收情况
            </h4>
         </div>
         <div class="modal-body">
             <div class="docmain">
             <form action="" method="post" id="checkinfo">
             <table class="table table-bordered table-hover change">		
				<tr>
				    <th></th>
					<th>部门</th>
					<th>姓名</th>
					<th>签收状况</th>
					<th>催收</th>
				</tr>
			
			<tbody id="checkinfotable" style="border:0px solid #2d2d2d;">
			
  			</tbody>
			</table>
	       </form>
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
	$("#meetAuditIndex").css({"background":"#c91306","color":"#ffffff"});
	ajaxSubmit($("#doselect"),setTable);
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
		 var datetime=times(this.createTime);
		 var meettime=times(this.meetTime);  
		 html+="<tr><td><input type='checkbox'  value='"+this.id+"'>&nbsp;"
		 +i+"</td><td>"+datetime+"</td><td id='lev'>"
		 +(this.level==1?"特急":this.level==2?"加急":this.level==3?"平急":this.level==4?"特提":"")+"</td><td>"+this.name+"</td><td>"+this.docNo+"</td><td>"+this.docTitle+"</td><td>"
		 +this.meetCompanyName+"</td><td>"+meettime+"</td><td><button type='button' class='btn btn-link' data-toggle='modal' data-target='#myModal' onclick=\"showinfo('"+this.id
		 +"','"+this.tid
		 +"','"+this.signStatus
		 +"')\">查看</button></td><td class='tdmiddle'>";
		 if(this.remark==null){
			 html+="<a data-toggle='modal' data-target='#checksign'  href='#' onclick=\"showcheckinfo('"+this.id+"')\">"+this.didtask+"/"+this.signNum+"</a>";
		 }
		 html+="</td></tr>";
	 	 i++;
	 	 
	 });
	 $("#tablebody").html(html);	 
	 lilist(page, totalPage,size);   
}
function next(url){
	$("#doselect").attr("action",url);
	ajaxSubmit($("#doselect"),setTable);
}
//showcheckinfo就是显示签收信息
function showcheckinfo(id){
	var url="<c:url value='/home/getCheckInfo?did="+id+"'/>";
	$("#checkinfo").attr("action",url );
	ajaxSubmit($("#checkinfo"),setcheckinfo);
}
function setcheckinfo(data){
	var html="";
	var i=1;
	 $.each(data,function() {
       var status=(this.signStatus==0?'已签收':this.signStatus==1?'未签收':'');
       var cuisou=(this.signStatus==0?'':this.signStatus==1?'<a href="#" onclick="urge(\''+this.did+'\',\''+this.oid+'\',this)">催收</a>':'');
		 html+="<tr><td>"+i+"</td><td>"+this.organame+"</td><td>"+(this.uname==null?"":this.uname)+"</td><td>"+status+"</td><td><a href='"+this.id+"'>"+cuisou+"</a></td></tr>";
	   i++;
	 });
	 $("#checkinfotable").html(html);
}
function geturl(i,size){
	return  "<c:url value='/meet/meetSelect?page=" + i + "&size=" + size + "'/>";
}
function btnsub(){
	var url="<c:url value='/meet/meetSelect'/>";
	$("#doselect").attr("action",url );
	ajaxSubmit($("#doselect"),setTable);
}
function del(){
	var page=$("#pages").val();
	var ids="";
	ids+=deleteData();
	var url="<c:url value='/meet/meetdelete?ids=" + ids + "&page="+page+"'/>";
	next(url);
}
function showinfo(id,tid,signStatus){
	$.ajax({
		url : "<c:url value='/meet/getMeet' />",
		type : 'post',
		dataType : 'json',
		data : {"id" : id},
		success : function(result) {
			var datetime=times(result.data.meetTime);
			$("#department").html("<b>接收单位：</b>&nbsp;"+result.data.departmentInfo);
			$("#organizeDepartmentInfo").html("<b>承办单位：</b>&nbsp;"+result.data.meetCompanyName);
			$("#docNo").html(result.data.docNo);
			$("#doclevel").html(result.data.level==1?"特急":result.data.level==2?"加急":result.data.level==3?"平急":result.data.level==4?"特提":"");
			$("#meetTime").html(datetime);
			$("#meetName").html(result.data.name);
			$("#contentname").html(result.data.contactName);
			$("#contentphone").html(result.data.contactPhone);
			$("#addr").html(result.data.addr);
			$("#doctitle").html("<b>发文标题：</b>&nbsp;"+result.data.docTitle);
			$("#docinfo").html("<b>发文摘要：</b>&nbsp;"+result.data.docSummary);
			var url="<c:url value='/meet/getAttach?pid="+id+"'/>";
			$("#getfj").attr("action",url );
			ajaxSubmit($("#getfj"),setfjtable);
		}
	});
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

function setfjtable(data){
	 var html="";
	 $.each(data,function() { 
		 var fileurl="<c:url value='/attachment/download?id=" + this.id + "'/>";
		 html+="<tr><td>"+this.fileName+"</td><td><a target=\"_blank\" href='"+fileurl+"'>查看</a></td></tr>";
	 });
	 $("#attach").html(html);
}
function urge(did,oid,obj){
	$.ajax({
		url : "<c:url value='/meet/meetUrge' />",
		type : 'post',
		dataType : 'json',
		data : {
			"mid" : did,
			"oid" : oid
			},
		success : function(result) {
			if(result.code==0){
				$(obj).parent().html("已催收");
			}else if(result.code==1){
				$(obj).parent().html(result.data);
			}
		}
	});
}
</script>
	
</body>
</html>
