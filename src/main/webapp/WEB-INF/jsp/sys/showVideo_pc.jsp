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
                            <video poster="" controls crossorigin id="videoMain">
                                <!-- Video files -->
                                <source id="videoSource" src="" type="video/mp4">
                                <!-- <source src="https://cdn.selz.com/plyr/1.0/movie.webm" type="video/webm"> -->

                                <!-- Text track file -->
                                <!-- <track kind="captions" label="English" srclang="en" src="https://cdn.selz.com/plyr/1.0/example_captions_en.vtt" default> -->

                                    <!-- Fallback for browsers that don't support the <video> element -->
                                    <!-- <a href="https://cdn.selz.com/plyr/1.0/movie.mp4">Download</a> -->
                            </video>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

<script type="text/javascript">

	$(function(){
		drawElement();
		
		initData();
		
		plyr.setup();
		
		$("[data-player='fullscreen']").click();
	});
	
	function drawElement(){
		var video=document.getElementById('videoMain');

		//视频结束触发得事件
		video.addEventListener("ended",function(){
           
		})
	}

	function initData(){
		<%-- var filePath="<%=basePath %>file/${videoPath}"
		$("#videoSource").attr("src",filePath); --%>
		
		var getVideoSteam = "<%=basePath %>system/fileController/getVideo?fileId=${videoId}";
		$("#videoSource").attr("src",getVideoSteam);
	}	
</script>
</html>
