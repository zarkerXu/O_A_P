<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<div class="col-lg-2 index-left">
	<br />
	<!-- 页面左侧链接组开始 -->
	<div class="list-group" id="list-group">
			<a id="home" class="list-group-item" href="<c:url value='/home/index' />"><i
			class="icon-chevron-right"></i> 首页</a> 
			<a id="docInputIndex" class="list-group-item"
			href="<c:url value='/doc/docInputIndex' />"><i
			class="icon-chevron-right"></i> 公文发布</a> 
			<a id="docReceiveIndex" class="list-group-item" href="<c:url value='/doc/docReceiveIndex' />"><i
			class="icon-chevron-right"></i> 公文接收</a> 
			<a id="docIndex" class="list-group-item"
			href="<c:url value='/doc/docIndex' />"><i
			class="icon-chevron-right"></i> 已发布公文</a> 
			<a id="meetInputIndex" class="list-group-item"
			href="<c:url value='/meet/meetInputIndex' />"><i
			class="icon-chevron-right"></i> 会议发布</a> 
			<a id="meetReceiveIndex" class="list-group-item"
			href="<c:url value='/meet/meetReceiveIndex' />"><i
			class="icon-chevron-right"></i> 会议接收</a> 
			<a id="meetIndex" class="list-group-item"
			href="<c:url value='/meet/meetIndex' />"><i
			class="icon-chevron-right"></i> 已发布会议</a> 
			<a id="meetCompanyIndex" class="list-group-item"
			href="<c:url value='/meet/meetCompanyIndex' />"><i
			class="icon-chevron-right"></i> 会议承办</a> 
			<a id="noticeAuditIndex" class="list-group-item"
			href="<c:url value='/others/noticeAuditIndex' />"><i
			class="icon-chevron-right"></i> 公告</a> 
			<a id="RelayIndex" class="list-group-item"
			href="<c:url value='/others/RelayIndex' />"><i
			class="icon-chevron-right"></i> 我的转发</a> 
			<a id="ApproveIndex" class="list-group-item"
			href="<c:url value='/others/ApproveIndex' />"><i
			class="icon-chevron-right"></i> 我的审批</a>
		<shiro:hasPermission name="commonlyadmin">
			<a id="docAuditIndex" class="list-group-item"
				href="<c:url value='/doc/docAuditIndex' />"><i
				class="icon-chevron-right"></i> 公文审计</a>
			<a id="meetAuditIndex" class="list-group-item"
				href="<c:url value='/meet/meetAuditIndex' />"><i
				class="icon-chevron-right"></i> 会议审计</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="superadmin">
			<a id="docAuditIndex" class="list-group-item"
				href="<c:url value='/doc/docAuditIndex' />"><i
				class="icon-chevron-right"></i> 公文审计</a>
			<a id="meetAuditIndex" class="list-group-item"
				href="<c:url value='/meet/meetAuditIndex' />"><i
				class="icon-chevron-right"></i> 会议审计</a>
		</shiro:hasPermission>
	</div>
	<!-- 页面左侧链接组结束 -->
</div>
