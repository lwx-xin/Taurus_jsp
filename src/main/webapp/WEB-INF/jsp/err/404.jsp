<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<% 
   String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/common.jsp"/>
</head>
<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h1>404</h1>
        <!-- <h3 class="font-bold">页面未找到！</h3> -->
        <h3 class="font-bold">${sysErrMessage }</h3>

        <div class="error-desc">
           <!--  抱歉，页面好像去火星了~ -->
            <form class="form-inline m-t" >
                <!-- <div class="form-group">
                    <input type="email" class="form-control" placeholder="请输入您需要查找的内容 …">
                </div> -->
                <button type="button" class="btn btn-primary" onclick="back();">返回首页</button>
            </form>
        </div>
    </div>

</body>
<script type="text/javascript">
	function back(){
		top.location.href="<%=basePath %>webLogin";//在顶层页面打开新页面
		//self.location.href="/url"; //当前页面打开URL页面
		//location.href="/url"; //当前页面打开URL页面
		//windows.location.href="/url"; //当前页面打开URL页面，前面三个用法相同。
		//this.location.href="/url"; //当前页面打开URL页面
		//parent.location.href="/url"; //在父页面打开新页面
		//top.location.href="/url"; //在顶层页面打开新页面
	}
</script>
</html>