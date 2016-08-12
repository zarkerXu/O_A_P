<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<div class="col-lg-2 index-left">
	<br />
	<!-- 页面左侧链接组开始 -->
	<div class="list-group">
	<shiro:hasPermission name="superadmin">
		<a id="index" class="list-group-item" href="<c:url value='/sys/index' />" ><i
			class="icon-chevron-right"></i> 组织管理</a>
		<a id="typeIndex" class="list-group-item" href="<c:url value='/sys/typeIndex' />" ><i
			class="icon-chevron-right"></i> 单位类型管理</a> 
		<a id="adminIndex" class="list-group-item" href="<c:url value='/sys/adminIndex' />" ><i
			class="icon-chevron-right"></i> 管理员管理</a>
		<a id="userIndex" class="list-group-item"
			href="<c:url value='/sys/userindex' />" ><i
			class="icon-chevron-right"></i> 联系人管理</a> 
		<a id="noticeIndex" class="list-group-item" href="<c:url value='/sys/noticeIndex' />" ><i
			class="icon-chevron-right"></i> 公告管理</a>
		
		
	</shiro:hasPermission>
	<shiro:hasPermission name="commonlyadmin">
	<a id="index" class="list-group-item" href="<c:url value='/sys/index' />" ><i
			class="icon-chevron-right"></i> 组织管理</a>
		<a id="userindex" class="list-group-item"
			href="<c:url value='/sys/userindex' />" ><i
			class="icon-chevron-right"></i> 联系人管理</a> 
		<a id="noticeIndex" class="list-group-item" href="<c:url value='/sys/noticeIndex' />" ><i
			class="icon-chevron-right"></i> 公告管理</a>
	</shiro:hasPermission>
	</div>
	
	<!-- 页面左侧链接组结束 -->
</div>
