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

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">H+</h1>

            </div>
            <h3>欢迎使用 H+</h3>

            <form class="m-t" role="form" method="post" action="<%=basePath %>webLogin">
                <div class="form-group">
                    <input type="text" name="userNumber" class="form-control" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="text" autocomplete="new-password" autocomplete="off" name="userPwd" class="form-control password-disc" placeholder="密码" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

                <p class="text-muted text-center"> 
	                <a href="#">
	                	<small>忘记密码了嗎？</small>
	                </a> | 
	                <a href="#">注册一个新账号嗎？</a>
                </p>
               <!-- 防止表单重复提交 -->
                <input type="hidden" name="loginToken" value="${LOGIN_TOKEN }" />
				
            </form>
        </div>
    </div>

</body>
<script type="text/javascript">
	$(function(){
		var sysErrMessage = '${sysErrMessage }';
		if(sysErrMessage != null && sysErrMessage != ''){
			layer.msg(sysErrMessage);
		}
	})
</script>
</html>
