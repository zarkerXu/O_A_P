<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="common/java.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<%@include file="common/css.jsp"%>

<!-- The styles -->
<style type="text/css">
</style>

</head>

<body class="login-bg">
	<div class="container">
		<!-- 页面主体开始 -->
		<div class="row login-form">
			<!-- 输入框 -->
			<form class="form-horizontal" method="post" action="<c:url value='/login' />">
			<div class="col-lg-10">
				
				  <div class="form-group form-group-lg">
				    <label class="col-lg-3 control-label login-label" for="inputUKey">请输入UKey口令：</label>
				    <div class="col-lg-9">
				      <input class="form-control" type="text" id="inputUKey" name="name" autofocus>
				    </div>
				  </div>
			</div>
			<!-- 登录按钮 -->
			<div class="col-lg-2">
				<button  type="submit" class="btn btn-success btn-lg btn-block" >登 录</button>
			</div>
			</form>
		</div>
		<!-- 页面主体结束 -->
	</div>
	<%@include file="common/js.jsp"%>

</body>
</html>
