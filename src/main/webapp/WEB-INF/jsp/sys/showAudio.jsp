<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<% 
   String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
<jsp:include page="../common/common.jsp"/>
<style type="text/css">
	body{
		padding-left:255px;
		padding-right:255px;
		/* background-color: #FFF !important; */
	}
</style>
</head>

<body class="gray-bg">
    <div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="player">
                            <audio controls>
                                <!-- Audio files -->
                                <source id="audioSource" src="" type="audio/mp3">

                                <!-- Fallback for browsers that don't support the <audio> element -->
                                <!-- 您的浏览器不支持在线播放，请<a href="http://music.baidu.com/cms/app/muplayer/test_mp3/1.mp3">下载</a> -->
                            </audio>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

<script type="text/javascript">

	$(function(){
		initData();
		
		//暂停
		plyr.setup();
		
	});

	function initData(){
		var filePath="<%=basePath %>file/${audioPath}"
		$("#audioSource").attr("src",filePath);
	}	
</script>
</html>
