<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div class="zhu-top">
	<div class="zhu-topm">
		<span class="zhu-topmb">公文传输系统</span>

		<ul class="list-inline">
			<li><a class="btn btn-link btn-lg" href="##"><i
					class="icon-user"></i> </a><span id="user_name"></span><span>,欢迎您！</span></li>
			<shiro:hasPermission name="admin">
				<li><a class="btn btn-link btn-lg"
					href="<c:url value='/sys/index' />"><i class="icon-cog"></i>
						系统管理</a></li>
			</shiro:hasPermission>
			<li><a class="btn btn-link btn-lg"
				href="<c:url value='/logout' />"><i class="icon-reply"></i> 退出</a></li>
		</ul>
	</div>
</div>
