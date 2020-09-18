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
<style type="text/css">
	div[name='fileName']{
		width:180px; 
		height:15px; 
		line-height:15px;
		overflow: hidden; /*溢出隐藏*/
		text-overflow: ellipsis; /*以省略号...显示*/
		white-space: nowrap; /*强制不换行*/
	}
	
	#fileMenuMain{
		/* position: relative !important; */
	}
	#fileMenuMain div{
		/* position: fixed !important; */
	}
	
	.add-children-folder{
		float:right;
	}
	.edit-folder{
		float:right;
		margin-right:10px;
	}
</style>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-3" id="fileMenuMain">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="file-manager">
                            <h5>路径：</h5>
                            <span id="nowPath">
	                            <!-- <a href="#" class="file-control active">所有</a>
	                            <a href="#" class="file-control">文档</a>
	                            <a href="#" class="file-control">视频</a>
	                            <a href="#" class="file-control">图片</a> -->
                            </span>
                            <div class="hr-line-dashed"></div>
                            <button class="btn btn-primary btn-block" id="uploadFileBtn" onclick="uploadFileClick();">添加文件</button>
                            <div class="hr-line-dashed"></div>
                            <h5>文件夹</h5>
                            <!-- <ul class="folder-list" style="padding: 0">
                                <li><a href="#"><i class="fa fa-folder"></i> 文件</a>
                                </li>
                                <li><a href="#"><i class="fa fa-folder"></i> 图片</a>
                                </li>
                                <li><a href="#"><i class="fa fa-folder"></i> Web页面</a>
                                </li>
                                <li><a href="#"><i class="fa fa-folder"></i> 插图</a>
                                </li>
                                <li><a href="#"><i class="fa fa-folder"></i> 电影</a>
                                </li>
                                <li><a href="#"><i class="fa fa-folder"></i> 书籍</a>
                                </li>
                            </ul> -->
                            <div id="folderTree" class="test"></div>
                           <!--  <h5 class="tag-title">标签</h5>
                            <ul class="tag-list" style="padding: 0">
                                <li><a href="#">爱人</a>
                                </li>
                                <li><a href="#">工作</a>
                                </li>
                                <li><a href="#">家庭</a>
                                </li>
                                <li><a href="#">孩子</a>
                                </li>
                                <li><a href="#">假期</a>
                                </li>
                                <li><a href="#">音乐</a>
                                </li>
                                <li><a href="#">照片</a>
                                </li>
                                <li><a href="#">电影</a>
                                </li>
                            </ul>
                            <div class="clearfix"></div> -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-9 animated fadeInRight">
                <div class="row">
                    <div class="col-sm-12" id="fileList">
                    
                    </div>
                    
					<div id="audioMenu" style="position:fixed;margin-top:200px;">
						<div id="navImg" style="float: left;">
							<img id="navImg" style="cursor:pointer;"
								width="50px" title="正在播放"
								src="<%=basePath %>js_css/img/music.png">
						</div>
						<div style="float:left;" id="audioDiv">
							<div class="ibox float-e-margins">
								<div class="player" id="audioList">
									<audio controls id="audioMain">
										<source id="audioSource" src="" type="audio/mp3">
									</audio>
								</div>
							</div>
						</div>
					</div>
					
                </div>
            </div>
        </div>
    </div>
    
</body>
<script>
	$(function(){
		//加载数据
		initData();
		
		//渲染控件
		drawElement()
		
	});
	
	//渲染控件
	var audioDivWidth=0;
	function drawElement(){
		//绑定鼠标悬浮在文件上的动画
		$('.file-box').each(function () {
			animationHover(this, 'pulse');
		});
		
		//渲染音频控件		
		plyr.setup();
		//控制音频控件伸缩
		var audioDivShow = false;
		audioDivWidth = $("#audioDiv").outerWidth()+5;
		$("#audioMenu").css("right","-"+audioDivWidth+"px");
		$("#navImg").click(function(){
			var thisObj = $("#audioMenu");
			if (audioDivShow) {
				thisObj.each(function() {
					$(this).animate({right : "-"+audioDivWidth}, 300);
				});
				audioDivShow = false;
			} else {
				thisObj.each(function() {
					$(this).animate({right : "0"}, 300);
				});
				audioDivShow = true;
			}
		});
		//音频控件默认不显示，点击音乐后显示
		$("#audioMenu").hide();
		
		//使左侧菜单跟随页面
        $(window).scroll(function() {
            var s = $(window).scrollTop();
            $("#fileMenuMain").css({"top" : s+"px"})
        })
        
        //添加文件按钮默认禁用
		$("#uploadFileBtn").prop("disabled",true);
	}
	
	//加载数据
	function initData(){
		//加载文件列表
		//initFileList("");
		//加载菜单列表
		initFolderList();
		
		rightClickEvent();
	}

	//加载文件列表
	function initFileList(folderId){
		$("#fileList").html("");
		var obj = {};
		obj["fileFolderId"]=folderId;
		$.ajax({
			url: "<%=basePath %>system/fileController/initFileList",
			data: obj,
			dataType: "json",
			type: "post",
			async: true,//false,同步；true,异步
			beforeSend: function () {
				loading();
            },
            complete: function () {
            	removeLoading();
            },
			success: function(data){
				if(data.status){
					var fileList = data.listData;//文件列表
					if(isNotNull(fileList)){
						$.each(fileList,function(index,fileInfo){
							var fileHtml=getTemplate(fileInfo);
							$("#fileList").append(fileHtml);
						});
					}
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("err");
			}
		});
	}
	
	//加载菜单列表
	var folderId="";
	function initFolderList(){
		$.ajax({
			url: "<%=basePath %>system/fileController/initFolderList",
			data: "",
			dataType: "json",
			type: "post",
			async: true,//false,同步；true,异步
			beforeSend: function () {
				loading();
            },
            complete: function () {
            	removeLoading();
            },
			success: function(data){
				if(data.status){
					var folderTree = data.listData;//菜单列表
					//console.log(JSON.stringify(folderTree));
					var folderTreeJson = getFolderTreeJson(folderTree,"");
					//console.log(JSON.stringify(folderTreeJson));
					$('#folderTree').treeview({
				        levels: 1,
				        data: folderTreeJson,
				        preventUnselect: true,
				        onNodeSelected: function (event, node) {
				            //选中节点
				            folderId = node.tags;
				            initFileList(folderId);
				            //initFilePath(nowPath);
							$("#uploadFileBtn").prop("disabled",false);
							rightClickEvent();
				        },
				        onNodeUnselected: function (event, node) {
				        	//取消选中节点
				            folderId = "";
				            $("#fileList").html("");
							$("#uploadFileBtn").prop("disabled",true);
							rightClickEvent();
				        },
				    });
					var nodeId = $("#folderTree li").eq(0).attr("id");
					$("#"+nodeId).click();
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("err");
			}
		});
	}
	
	function rightClickEvent(){
		var thisFolderId="";
		$("#folderTree li").contextMenu({
			width: 110, // width
			itemHeight: 30, // 菜单项height
			bgColor: "#FFF", // 背景颜色
			color: "#676a6c", // 字体颜色
			fontSize: 12, // 字体大小
			hoverBgColor: "#f5f5f5", // hover背景颜色
			target: function(ele) { // 当前元素
				var nodeId = $(ele).attr("id");
				thisFolderId = $('#folderTree').treeview('findNodes', [nodeId,'id'])[0].tags;
			},
			menu: [{ // 菜单项
					text: "新增文件夹",
					icon: "<%=basePath %>js_css/img/addFolder.png",
					callback: function() {
						if(isNotNull(thisFolderId)){
							layer.open({
								  type: 2,
								  title: "新增文件夹",
								  area: ['800px', '500px'],
								  fixed: false, //不固定
								  maxmin: true,
								  content: '<%=basePath %>system/folderController/openAddFolder?folderId='+thisFolderId
							});
						}
					}
				},
				{
					text: "编辑文件夹",
					icon: "<%=basePath %>js_css/img/editFolder.png",
					callback: function() {
						if(isNotNull(thisFolderId)){
							layer.open({
								  type: 2,
								  title: "编辑文件夹",
								  area: ['800px', '500px'],
								  fixed: false, //不固定
								  maxmin: true,
								  content: '<%=basePath %>system/folderController/openUpdateFolder?folderId='+thisFolderId
							});
						}
					}
				}
			]

		});
	}
	
	/* function initFilePath(nodeTags){
		var nowPathHtml="";
		if(isNotNull(nodeTags)){
			var folderArr = nodeTags.split("/");
			for(var i=0; i<folderArr.length; i++){
				var folder = folderArr[i];
				var classHtml = ""
				if(i==folderArr.length-1){
					classHtml=" active";
				}
				if(i==0){
					nowPathHtml+="<a href='#' style='margin-right:0px;' class='file-control"+classHtml+"'>"+user[folder]+"</a>"
				} else {
					nowPathHtml+=" / <a href='#' style='margin-right:0px;' class='file-control"+classHtml+"'>"+folder+"</a>"
				}
			}
		}
		$("#nowPath").html(nowPathHtml);
	} */
	
	//把后端返回的文件列表转换数据结构，前端显示
	function getFolderTreeJson(folderTree){
		var nodeList = new Array();
		
		if(isNotNull(folderTree)){
			$.each(folderTree, function(index, folderEntity){
				var folderId = folderEntity.folderId;
				var folderName = folderEntity.folderName;
				var childrenNode = folderEntity.childrenNode;
				var nodes = getFolderTreeJson(childrenNode);
				
				var bottonHtml="";
				//bottonHtml+="<button type='button' class='btn btn-primary btn-xs add-children-folder'>新增子菜单</button>";
				//bottonHtml+="<button type='button' class='btn btn-primary btn-xs edit-folder'>编辑</button>";
				
				var node = new Object();
				node["text"] = folderName + bottonHtml;
				node["tags"] = folderId;
				node["icon"] = "fa fa-folder-o";
				node["id"] = folderId;
				if(isNotNull(nodes)){
					node["nodes"] = nodes;
				}
				nodeList.push(node);
			});
		}
		return nodeList;
	}
	
	function getTemplate(fileInfo){
		//文件id
		var fileId=fileInfo.fileId;
		//文件类型
		var fileType=fileInfo.fileType;
		//文件名-未处理
		var fileNameRealy=fileInfo.fileNameRealy;
		//文件名-处理后
		var fileName=fileInfo.fileName;
		//文件大小，单位kb
		var fileSize=fileInfo.fileSize;
		//文件所属文件夹id
		var fileFoderId=fileInfo.fileFoderId;
		//删除标志
		var fileDelFlg=fileInfo.fileDelFlg;
		//创建时间
		var fileCreateTime=formatDate(new Date(fileInfo.fileCreateTime), "yyyy-MM-dd HH:mm");
		//文件图标
		var iconHtml=getIconByFileType(fileType);
		
		var templateHtml=""+
		"<div class='file-box' title='"+fileNameRealy+"'>"+
		"	<div class='file'>"+
		"		<a href='#' file-type='"+fileType+"' file-id='"+fileId+"' file-name='"+fileNameRealy+"' onclick='openFile(this);'>"+
		"			<span class='corner'></span>"+
		"			<div class='icon'>"+iconHtml+"</div>"+
		"			<div class='file-name'>"+
		"				<div name='fileName'>"+fileNameRealy+"</div>"+
		"				<small>添加时间：<span name='fileCreateTime'>"+fileCreateTime+"</span></small>"+
		"			</div>"+
		"		</a>"+
		"	</div>"+
		"</div>";
		return templateHtml;
	}
	
	function openFile(ele){
		var fileId=$(ele).attr("file-id");
		var fileType=$(ele).attr("file-type");
		var fileName=$(ele).attr("file-name");
		
		var filePath = getFilePath(fileId);
		if(isNull(filePath)){
			return false;
		}

		switch (fileType) {
			case "log":
				//日志
				<%-- top.location.href="<%=basePath %>file/"+filePath; --%>
				parent.addMenuTab("<%=basePath %>file/"+filePath,fileName,1);
				break;
			case "video":
				//视频
				openVideo(filePath, fileName);
				break;
			case "txt":
				//文本
				<%-- top.location.href="<%=basePath %>file/"+filePath; --%>
				parent.addMenuTab("<%=basePath %>file/"+filePath,fileName,1);
				break;
			case "audio":
				//音频
				openAudio(filePath, fileName);
				break;
			case "picture":
				//图片
				<%-- top.location.href="<%=basePath %>file/"+filePath; --%>
				<%-- window.open("<%=basePath %>file/"+filePath); --%>
				parent.addMenuTab("<%=basePath %>file/"+filePath,fileName,1);
				break;
			case "word":
				//文档
				break;
			case "pdf":
				//pdf
				openPdf(filePath, fileName);
				break;
			case "excel":
				//excel
				break;
			case "rar":
				//压缩包
				openRar(filePath);
				break;
			default:
				//未知
				break;
		}
	}
	
	//根据文件分组获取显示图标
	function getIconByFileType(fileType){
		var iconHtml="";
		switch (fileType) {
			case "log":
				//日志
				//iconHtml="<i class='fa fa-file-code-o'></i>";
				iconHtml="<i class='fa fa-file-text-o'></i>";
				break;
			case "video":
				//视频
				iconHtml="<i class='img-responsive fa fa-film'></i>";
				break;
			case "txt":
				//文本
				iconHtml="<i class='fa fa-file'></i>";
				break;
			case "audio":
				//音频
				iconHtml="<i class='fa fa-music'></i>";
				break;
			case "picture":
				//图片
				iconHtml="<i class='fa fa-file-picture-o'></i>";
				break;
			case "word":
				//文档
				iconHtml="<i class='fa fa-file-word-o'></i>";
				break;
			case "pdf":
				//pdf
				iconHtml="<i class='fa fa-file-pdf-o'></i>";
				break;
			case "excel":
				//excel
				iconHtml="<i class='fa fa-bar-chart-o'></i>";
				break;
			case "rar":
				//压缩包
				iconHtml="<i class='fa fa-file-zip-o'></i>";
				break;
			default:
				//未知
				iconHtml="<i class='fa fa-file-o'></i>";
				break;
		}
		return iconHtml;
	}
	
	//获取文件路径
	function getFilePath(fileId){
		var filePath = "";
		var obj = {};
		obj["fileId"]=fileId;
		$.ajax({
			url: "<%=basePath %>system/fileController/getFilePath",
			data: obj,
			dataType: "json",
			type: "post",
			async: false,//false,同步；true,异步
			success: function(data){
				if(data.status){
					filePath = data.objectData;
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("err");
			}
		});
		return filePath;
	}
	
	//文件上传
	function uploadFileClick(){
		if(isNull(folderId)){
			swal("", "请选择文件夹", "error");
			return false;
		}
		var uploadUrl = "<%=basePath %>system/fileController/openUploadFile?folderId="+folderId;
		parent.addMenuTab(uploadUrl,"文件上传",1)
	}
	
	//打开视频
	function openVideo(videoPath, videoName){
		var url = "<%=basePath %>system/fileController/openShowVideo?videoPath="+videoPath;
		//window.open(url);
		parent.addMenuTab(url,videoName,1);
	}
	
	//打开音频
	function openAudio(audioPath, audioName){
		<%-- var url = "<%=basePath %>system/fileController/openShowAudio?audioPath="+audioPath;
		window.open(url);//重新打开一个网页
		parent.addMenuTab(url,audioName,1);//重新打开一个tabs --%>
		
		var filePath="<%=basePath %>file/"+audioPath;
		var audioHtml=''+
		'<div class="ibox float-e-margins">'+
		'	<div class="player" id="audioList">'+
		'		<audio id="audioMain" autoplay="autoplay" controls>'+
		'			<source id="audioSource" src="'+filePath+'" type="audio/mp3">'+
		'		</audio>'+
		'	</div>'+
		'</div>';
		
		$("#audioDiv").html(audioHtml);
		plyr.setup({
			"click":false
		});
		$("#audioMenu").css("right","-"+audioDivWidth+"px");
		$("#audioMenu").show();
		//$("[data-player='play']").click();
		
		//音乐播放完毕，隐藏控件
		var audio=document.getElementById('audioMain');
		//音乐结束触发得事件
		audio.addEventListener("ended",function(){
			$("#audioMenu").hide();
		})
	}
	
	//打开pdf文件
	function openPdf(pdfPath,pdfName){
		var url = "<%=basePath %>system/fileController/openPdf?pdfPath="+pdfPath+"&pdfName="+pdfName;
		parent.addMenuTab(url,pdfName,1);
	}
	
	//打开压缩包
	function openRar(filePath){
		swal({
			title: "",
			text: "暂不支持在线打开，是否要下载",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
		.then((willDelete) => {
			if (willDelete) {
				window.location.href="<%=basePath %>system/fileController/downloadFile?filePath="+filePath;
			}
		});
	}
	
	function openLog(){
		var url = "<%=basePath %>system/fileController/openShowVideo?videoPath="+videoPath;
		parent.addMenuTab(url,videoName,1);
	}
	
</script>

</html>
