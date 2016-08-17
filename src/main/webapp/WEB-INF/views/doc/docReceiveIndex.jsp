<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<!-- The styles -->
<%@include file="../common/css.jsp"%>
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
				<li class="active">公文接收</li>
			</ol>
		</div>
		<!-- 页面顶部结束 -->

		<!-- 表单开始 -->
		<form id="selectForm" action="<c:url value='/doc/docReceive' />"
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
							<label for="docTitle2" class="col-xs-5 control-label">文件标题：</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="docTitle2" name="docTitle">
							</div>
						</div>

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
					<th>发文编号</th>
					<th>文件标题</th>
					<th>发文单位</th>
					<th>内容</th>
					<th>签收状态</th>
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
	</div>
	     <input type="hidden" value="" id="pages"/>
	
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
              	 公文转发
            </h4>
         </div>
         <div class="modal-body">
         <form id="zfForm" action="<c:url value='/others/Relay' />"
			method="post">
			<div class="form-group">
			<label for="name">转发意见:</label>
			 
           <textarea class="form-control textareatop "   id="relayRemark" name="relayRemark" ></textarea>
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
           <input name="type" type="hidden" value="1">
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

ajaxSubmit($("#selectForm"),setTable);
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
		 var datetime=date.getFullYear()+"/"+month+"/"+date.getDate()+" "+date.getHours()+":"+minutes+":"+seconds;  
		 html+="<tr><td>&nbsp;"
		 +i+"</td><td>"+datetime+"</td><td id='lev'>"+(this.level==1?"特急":this.level==2?"加急":this.level==3?"平急":"特提")+"</td><td>"+this.docNo+"</td><td>"+this.docTitle+"</td><td><p class='chaochu'>"
		 +this.sendDepartmentInfo+"</p></td><td><button type='button' class='btn btn-link' onclick=\"showdoc('"+this.id
				 +"','"+this.tid
				 +"','"+this.signStatus
				 +"','"+this.sendDepartmentInfo
				 +"')\">查看</button></td><td class='tdmiddle'>"+(this.signStatus==0?"已签收":"未签收")+"</td><td><a href='#' onclick='relay(\""+this.id+"\")'>转发</a>  ";
		
				 html+= "</td></tr>"
				
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
	var url="<c:url value='/doc/docReceive'/>";
	$("#selectForm").attr("action",url );
	ajaxSubmit($("#selectForm"),setTable);
}
function geturl(i,size){
	return  "<c:url value='/doc/docReceive?page=" + i + "&size=" + size + "'/>";
}
function showdoc(id,tid,signStatus,senddepartment){
	$.ajax({
		url : "<c:url value='/doc/getDoc' />",
		type : 'post',
		dataType : 'json',
		data : {"id" : id},
		success : function(result) {
			showinfo(result.data.docNo,result.data.level,result.data.docTitle,result.data.docSummary,result.data.id,tid,signStatus,senddepartment);
		}
		});
}

function showinfo(docNo,level,docTitle,docSummary,id,tid,signStatus,senddepartment){
    $("#senddepartment").html("<b>发文单位：</b>&nbsp;"+senddepartment);
	$("#docNo").html(docNo);
	$("#doclevel").html(level==1?"特急":level==2?"加急":level==3?"平急":"特提");
	$("#doctitle").html("<b>文件标题：</b>&nbsp;"+docTitle);
	$("#docinfo").html("<b>发文摘要：</b>&nbsp;"+docSummary);

	$.ajax({
		url : "<c:url value='/doc/getAttach' />",
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
			 $("#myModal").modal("show");
		}
	});
	
}
function sign(signStatus,tid){
	if(signStatus==1){
		$.ajax({
			url : "<c:url value='/doc/docSign' />",
			type : 'post',
			dataType : 'json',
			data : {"tid" : tid},
			success : function(result) {
				
				ajaxSubmit($("#selectForm"),setTable);
			}
		});
	}
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
	$("#docReceiveIndex").css({"background":"#c91306","color":"#ffffff"});
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
		$("#relayRemark").val("");
	});
}
	</script>
</body>
</html>
