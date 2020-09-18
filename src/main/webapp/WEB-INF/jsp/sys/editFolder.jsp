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
	.no-padding{
		padding: 0px;
	}
	.chosen-container-single .chosen-single{
		border: 1px solid #e5e6e7;
	}
	.custom-chosen-drop{
		width:490px !important;
		margin-left: 15px;
	}
</style>
</head>
<body>
    <div class="wrapper animated fadeInRight">
    	<div class="row">
            <div class="col-sm-12 no-padding">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="my-form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                    <input id="folderName" name="folderName" class="form-control" type="text" aria-required="true">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 最多100个文字</span>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="button" id="editFolderBtn" onclick="editInfo();">提交</button>
                                </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
		</div>
	</div>
</body>
<script type="text/javascript">

	$(function(){

		//渲染控件
		drawElement();
		
		initData();
	});
	
	//渲染控件
	function drawElement(){
	}
	
	function initData(){
		if("${editType}"=="update"){
			
		} else if("${editType}"=="insert"){
			return false;
		}
		
		var obj = {};
		obj["folderId"] = '${folderId}';
		//obj["editType"] = "${editType}";
		//obj["folderName"] = $("#folderName").val();
		$.ajax({
			url: "<%=basePath %>system/folderController/initEditFolder",
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
					$("#folderName").val(data.objectData.folderName);
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("ajax err");
			}
		});
	}
	
	function editInfo(){
		var param = {};
		param["folderId"] = "${folderId}";
		//名称
		param["folderName"] = $("#folderName").val();
		param["editType"] = "${editType}";
		
		$.ajax({
			url: "<%=basePath %>system/folderController/editFolderDetail",
			data: param,
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
					swal("编辑成功", {icon: "success",})
					.then((value) => {
						closeFrame()
					});
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("ajax err");
			}
		});
	}
	
	function closeFrame(){
		parent.location.reload();//重新加载父页面
	}
	
</script>
</html>