<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<% 
   String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<script src="<%=basePath %>js_css/js/pdfobject/pdfobject.min.js"></script>
<script src="<%=basePath %>js_css/js/jquery.min.js?v=2.1.4"></script>
</head>
<body>

</body>
<script type="text/javascript">
$(function(){
	var filePath="<%=basePath %>file/${pdfPath}";
	PDFObject.embed(filePath);
});
</script>
</html>