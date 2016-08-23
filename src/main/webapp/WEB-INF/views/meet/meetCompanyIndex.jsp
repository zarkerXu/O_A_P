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
				<li class="active">会议承办</li>
			</ol>
		</div>
		<!-- 页面顶部结束 -->

		<!-- 表单开始 -->
		<form id="selectForm" action="<c:url value='/meet/meetCompany' />"
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
							<label for="docNo2" class="col-xs-5 control-label">会议名称：</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="name" name="name">
							</div>
						</div>

					</div>
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
				</div>
				<div class="row" style="margin-top: 10px; margin-bottom: 10px">
					<div class="col-md-4">


					</div>
					<div class="col-md-4">

					</div>
					<div class="col-md-4" >
						<div class="pull-right">
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
               <div class="title" id="senddepartment">  
               
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
                 	<div class="second" id="contactPhone"> 
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
      <input type="hidden" value="" id="pages"/>
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
             会议报名情况  <a id="export" href=''><button type="button" class="btn btn-success">导出</button></a>
            </h4>
         </div>
         <div class="modal-body" id="exportTables">
         <h1 class="alreadyh">参会人员</h1>
	<table class="table table-bordered table-hover change">
		<thead>
		<tr>
			<th>序号</th>
			<th>单位</th>
			<th>名称</th>
			<th>性别</th>
			<th>职位</th>
			<th>电话</th>
			<th>备注</th>
			<th>审批状态</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody id="tb0">
		</tbody>
	</table>
	<h1 class="alreadyh">听会人员</h1>
	<table class="table table-bordered table-hover change">
		<thead>
		<tr>
			<th>序号</th>
			<th>单位</th>
			<th>名称</th>
			<th>性别</th>
			<th>职位</th>
			<th>电话</th>
			<th>备注</th>
			<th>审批状态</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody id="tb1">
		</tbody>
	</table>
	<h1 class="alreadyh">请假人员</h1>
	<table class="table table-bordered table-hover change">
		<thead>
		<tr>
			<th>序号</th>
			<th>单位</th>
			<th>名称</th>
			<th>性别</th>
			<th>职位</th>
			<th>电话</th>
			<th>备注</th>
			<th>审批状态</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody id="tb2">
		</tbody>

	</table>
         </div>
         <div class="modal-footer">
          <button id="unpassButton" type="button" style="display: none;" class="btn btn-success btn-sm" onclick="meetunpass();"
               data-dismiss="modal">报名重开
            </button>
         <button id="passButton" type="button" class="btn btn-success btn-sm" onclick="meetpass();"
               data-dismiss="modal">报名截止
            </button>
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
         </div>
      </div>
</div>
</div>
<div class="modal fade " id="spModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <button id="spOpen" style="display: none;" type="button" data-toggle='modal' data-target='#spModal' ></button>
   <div class="modal-dialog docmodel">
     <form id="adduserform" action="<c:url value='/meet/meetUserPass' />" method="post">
     <input type="hidden" name="id" id="emid" />
     <input type="hidden" name="pass" id="pass" value="false" />
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="addLabel">
               		人员审批
            </h4>
         </div>
         <div class="modal-body">
           审批反馈：
           <textarea class="form-control textareatop "  id="passremark" name="passRemark" ></textarea>
         </div>
         
         <div class="modal-footer">
            <button type="button" class="btn btn-danger leftbutton" data-dismiss="modal">取消
            </button>
            <button type="button" class="btn btn-success" onclick="spsubmit();">确定
            </button>
         </div>
      </div>
       </form>
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
var mid="";
$(document).ready(function(){
	$("#meetCompanyIndex").css({"background":"#c91306","color":"#ffffff"});
	setOrganization();
	ajaxSubmit($("#selectForm"),setTable);
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
    var url = "#";
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
		 +this.meetCompanyName+"</td><td>"+meettime+"</td><td><button type='button' class='btn btn-link' data-toggle='modal' data-target='#myModal' onclick=\"showinfo('"+this.id
				 +"','"+this.tid
				 +"','"+this.signStatus
				 +"')\">查看</button></td><td class='tdmiddle'>"+(this.passStatus==0?"已审批":this.passStatus==1?"审批未通过":this.passStatus==2?"待审批":this.passStatus==3?"未审批":"")+
				 "</td><td>";
		 if(this.passStatus==2||this.passStatus==3){
			 html+='<button type="button" class="btn btn-link" data-toggle="modal" data-target="#checksign" onclick="getPass(\''+this.id+'\',false)">审批报名</button>';
		 }else{
			 html+='<button type="button" class="btn btn-link" data-toggle="modal" data-target="#checksign" onclick="getPass(\''+this.id+'\',true)">查看报名</button>';
		 }
	     html+="</td></tr>";
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
	var url="<c:url value='/meet/meetCompany'/>";
	$("#selectForm").attr("action",url );
	ajaxSubmit($("#selectForm"),setTable);
}
function geturl(i,size){
	return  "<c:url value='/meet/meetCompany?page=" + i + "&size=" + size + "'/>";
}
function showcheckinfo(id){
	$.ajax({
		url : "<c:url value='/home/getCheckInfo' />",
		type : 'post',
		dataType : 'json',
		data : {"did" : id},
		success : function(result) {
			var html="";
			var i=1;
			 $.each(data,function() {
		       var status=(this.passStatus==0?'审批通过':this.passStatus==1?'审批未通过':his.passStatus==2?'提交审批':his.passStatus==3?'未审批':'');
		       var cuisou=(this.signStatus==2||this.signStatus==3?'催收':'');
		       var cz='<button type="button" class="btn btn-link" data-toggle="modal" data-target="#checksign" onclick="showcheckinfo(\''+this.id+'\')" >查看报名</button>';
				 html+="<tr><td>"+i+"</td><td>"+this.organame+"</td><td>"+(this.uname==null?"":this.uname)+"</td><td>"+status+"</td><td><a href='"+this.id+"'>"+cuisou+"</a></td></tr>";
			   i++;
			 });
			 $("#checkinfotable").html(html);
		}
	});
}
function showinfo(id,tid,signStatus){
	$.ajax({
		url : "<c:url value='/meet/getMeet' />",
		type : 'post',
		dataType : 'json',
		data : {"id" : id},
		success : function(result) {
			var datetime=times(result.data.meetTime);
			$("#senddepartment").html("<b>发文单位：</b>"+result.data.sendDepartmentInfo);
			$("#department").html("<b>接收单位：</b>&nbsp;"+result.data.departmentInfo);
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
					 $.each(result.data,function() {
						 var fileurl="<c:url value='/attachment/download?id=" + this.id + "'/>";
						 html+="<tr><td>"+this.fileName+"</td><td><a target=\"_blank\" href='"+fileurl+"'>查看</a></td></tr>";
					 });
					 $("#attach").html(html);
				}
			});
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
function limitsize(i){
	for(j=2;j<i+1;j++){
	var organ=$("#organize_"+j).html();
	var size=organ.length;
	if(size>7){
		$("#organize_"+j).html((organ.substring(0,3)+"..."+organ.substring(size-4,size)));
	}else{
		$("#organize_"+j).html(organ);
	}
	}
}
function getPass(id,b){
	mid=id;
	
	if(b){
		$("#passButton").hide();
	    $("#unpassButton").show();
	}else{
		$("#passButton").show();
		$("#unpassButton").hide();
	}
	$("#export").attr("href","<c:url value='/meet/passExcel' />"+"?mid="+id);
	$.ajax({
		url : "<c:url value='/meet/getMEntryByPassStatus' />",
		type : 'post',
		dataType : 'json',
		data : {"mid":id},
		success : function(result) {
			$("#tb0").html("");
			$("#tb1").html("");
			$("#tb2").html("");
			var i=1;
			$.each(result.data,function() {
				$("#tb"+this.type).append("<tr><td>"+i+++"</td><td style='width:130px;' ><a href='#' style='color:#000;text-decoration:none;' title='"+this.organame+"' id='organize_"+i+"'>"
						+this.organame+"</a></td><td>"
						+this.name+"</td><td>"
						+(this.sex==0?"男":"女")+"</td><td>"
						+this.post+"</td><td>"
						+(this.phone==null?"":this.phone)+"</td><td limit='6'>"
						+this.remark+'</td><td>'
						+(this.passStatus==0?"已审批":this.passStatus==1?"审批未通过":this.passStatus==2?"正在审批":this.passStatus==3?"未审批":"")
						+(b||this.passStatus==0||this.passStatus==1?"</td><td></td>":'</td><td><button type="button" class="btn btn-success btn-sm" onClick="pass(\''+this.id+'\');" >通过</button>   <button type="button" class="btn btn-danger btn-sm" onclick="nopass(\''+this.id+'\');">退回</button></td>')
						+"</tr>");
			});
			$("[limit]").limit();
			limitsize(i);
		}
	});
}

function pass(id){
	$.ajax({
		url : "<c:url value='/meet/meetUserPass' />",
		type : 'post',
		dataType : 'json',
		data : {"id":id,"pass":true},
		success : function(result) {
			getPass(mid);
		}
	});
}
function nopass(id){
	$("#emid").val(id);
	$("#passremark").val("");
	$("#spOpen").click();
}

function spsubmit(){
	ajaxSubmit($("#adduserform"),function(result){
		$("#spModal").modal("hide");
		getPass(mid);
	});
}
function meetpass(){
	$.ajax({
		url : "<c:url value='/meet/meetPass' />",
		type : 'post',
		dataType : 'json',
		data : {"id":mid},
		success : function(result) {
			if(result.code==0){
				$("#checksign").modal("hide");
				ajaxSubmit($("#selectForm"),setTable);
			}
		}
	});
}

function meetunpass(){
	$.ajax({
		url : "<c:url value='/meet/meetUnpass' />",
		type : 'post',
		dataType : 'json',
		data : {"id":mid},
		success : function(result) {
			if(result.code==0){
				$("#checksign").modal("hide");
				ajaxSubmit($("#selectForm"),setTable);
			}
		}
	});
}
	</script>
</body>
</html>
