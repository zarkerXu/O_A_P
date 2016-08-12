<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<!-- The styles -->
<%@include file="../common/css.jsp"%>
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
				<li class="active">公文审计</li>
			</ol>
		</div>
		<!-- 页面顶部结束 -->

		<!-- 表单开始 -->
		<form id="dotest" action="<c:url value='/doc/docSelect' />"
			method="post">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-4">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="docCompany7" class="col-xs-6 control-label">等&nbsp;
								&nbsp; &nbsp; &nbsp;级：</label>
							<div class="col-xs-6">
								<select class="form-control" id="level1" name="level">
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
				<div class="row" >
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
					<div class="col-md-4" >
					<div class="form-horizontal">
						<div class="pull-right" >
							<button type="button" class="btn btn-default" onclick="btnsub()">
								<strong>查 询</strong>
							</button>
							<button type="button" class="btn btn-default" onclick="del()">
								<strong>删 除</strong>
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
					<th><input type="checkbox" onClick="allSelect()">序号</th>
					<th>发布时间</th>
					<th>发文编号</th>
					<th>文件标题</th>
					<th>发文单位</th>
					<th>内容</th>
					<th>已签收/应签收</th>
					<th>等级</th>
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
<input type="hidden" value="" id="pages"/>
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
             公文签收情况
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
	$("#docAuditIndex").css({"background":"#c91306","color":"#ffffff"});
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
		 html+="<tr><td><input type='checkbox'  value='"+this.id+"'>&nbsp;"
		 +i+"</td><td>"+datetime+"</td><td>"+this.docNo+"</td><td>"+this.docTitle+"</td><td><p class='chaochu'>"
		 +this.sendDepartmentInfo+"</p></td><td><button type='button' class='btn btn-link' data-toggle='modal' data-target='#myModal' onclick=\"showdoc('"+this.id
		 +"','"+this.tid
		 +"','"+this.signStatus
		 +"','"+this.sendDepartmentInfo
		 +"')\">查看</button></td>";
		 if(this.remark==null){
			 html+="<td class='tdmiddle'><a data-toggle='modal' data-target='#checksign'  href='javascript:(0)' onclick=\"showcheckinfo('"+this.id+"')\">"+this.didtask+"/"+this.signNum+"</a>";
		 }else{
			 html+="<td></td>";
		 }
		 html+="<td id='lev'>"
		 +(this.level==1?"特急":this.level==2?"加急":this.level==3?"平急":"特提")+'</td></tr>';
	 	 i++;
	 		});
	 $("#tablebody").html(html);	 
	 lilist(page, totalPage,size);   
}
function next(url){
	$("#dotest").attr("action",url);
	ajaxSubmit($("#dotest"),setTable);
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
function del(){
	var page=$("#pages").val();
	var ids="";
	ids+=deleteData();
	var url="<c:url value='/doc/docdelete?ids=" + ids + "&page="+page+"'/>";
	next(url);
}

function btnsub(){
	var url="<c:url value='/doc/docSelect'/>";
	$("#dotest").attr("action",url );
	ajaxSubmit($("#dotest"),setTable);
}
function geturl(i,size){
	return  "<c:url value='/doc/docSelect?page=" + i + "&size=" + size + "'/>";
}
function showdoc(id,tid,signStatus,senddepartment){
	$.ajax({
		url : "<c:url value='/doc/getDoc' />",
		type : 'post',
		dataType : 'json',
		data : {"id" : id},
		success : function(result) {
			showinfo(result.data.docNo,result.data.level,result.data.docTitle,result.data.docSummary,result.data.id,signStatus,senddepartment);
		}
		});
}
function showinfo(docNo,level,docTitle,docSummary,id,signStatus,senddepartment){
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
			 $.each(result.data,function() {
				 var fileurl="<c:url value='/attachment/download?id=" + this.id + "'/>";
				 html+="<tr><td>"+this.fileName+"</td><td><a target=\"_blank\" href='"+fileurl+"'>查看</a></td></tr>";
			 });
			 $("#attach").html(html);
			 $("#myModal").modal("show");
		}
	});
	
}
function urge(did,oid,obj){
	$.ajax({
		url : "<c:url value='/doc/docUrge' />",
		type : 'post',
		dataType : 'json',
		data : {
			"did" : did,
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
