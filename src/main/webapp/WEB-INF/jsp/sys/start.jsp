<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/common.jsp"/>
<style type="text/css">
	.loding{
		position: relative; /*脱离文档流*/
        top: 50%; /*偏移*/
	}
</style>
</head>
<body>
</body>
<script type="text/javascript">
</script>
</html>