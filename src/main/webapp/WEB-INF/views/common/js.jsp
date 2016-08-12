<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- IE8 -->
<script type="text/javascript" src="<c:url value='/resources/js/tool/jquery.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/tool/jquery.validate.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/tool/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/page/formhelper.js' />"></script>

<!-- Bootstrap -->
<script type="text/javascript" src="<c:url value='/resources/js/tool/bootstrap.min.js' />" ></script>
<!-- Respond.js (为了使Bootstrap响应式兼容IE8) -->
<script type="text/javascript" src="<c:url value='/resources/js/page/respond.js' />"></script>
<!-- html5shiv.js (为了能在IE8中使用HTML5) -->
<script type="text/javascript" src="<c:url value='/resources/js/tool/html5shiv.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/tool/bootstrap-table.js' />" ></script>
<script type="text/javascript" src="<c:url value='/resources/js/common/common.js' />"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "<c:url value='/sys/getMyUser' />",
			type : 'post',
			dataType : 'json',
			success : function(result) {
				$("#user_name").text(result.data.orgname+" "+result.data.name);
			}
		});
	});
</script>