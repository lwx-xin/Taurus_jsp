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
</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeIn">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>文件上传</h5>
                    </div>
                    <div class="ibox-content">
                        <form id="my-awesome-dropzone" class="dropzone" action="" enctype="multipart/form-data">
                            <div class="dropzone-previews"></div>
                            <button type="submit" id="uploadBtn" class="btn btn-primary pull-right">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
<script>
   /*  $(document).ready(function () {
    }); */
    
    $(function(){
    	drawElement();
    });

    function drawElement(){
        Dropzone.options.myAwesomeDropzone = {
            url: "<%=basePath %>system/fileController/fileUpload", //必须填写
            params:{"folderId" : "${folderId}"},
            method:"post",  //也可用put
            autoProcessQueue: false,
            uploadMultiple: true,
            parallelUploads: 100,
            maxFiles: 100,
    	    
    	    /* 插件消息翻译 */
    	   /*  dictDefaultMessage: '<i class="fa fa-cloud-upload"></i>拖拉文件上传<br />或 <i class="fa fa-thumbs-down"></i>点此上传', */
    	    dictInvalidFileType: '仅支持以下格式文件：.doc,.docx,.xls,.xlsx,.ppt,.pptx,.zip,.rar,.7z,.txt,image/*,application/pdf,.psd',
    	    dictFileTooBig: '文件超出最大10M约束',
    	    dictMaxFilesExceeded: '超出最大上传数量',
    	    dictCancelUpload: '取消上传',
    	    dictRemoveFile: '删除',
    	    dictCancelUploadConfirmation: '确认取消上传',
    	    dictResponseError:"文件上传失败!",

            // Dropzone settings
            init: function () {
                var myDropzone = this;

                this.element.querySelector("button[type=submit]").addEventListener("click", function (e) {
                    e.preventDefault();
                    e.stopPropagation();
                    myDropzone.processQueue();
                });
                this.on("sendingmultiple", function () {});
                this.on("successmultiple", function (files, response) {});
                this.on("errormultiple", function (files, response) {});
                this.on("addedfile", function(file) { 
                    //上传文件时触发的事件
                });
                this.on("queuecomplete",function(file) {
                    //上传完成后触发的方法
                });
                this.on("removedfile",function(file){
                    //删除文件时触发的方法
                });
            }

        }
    }
</script>
</html>