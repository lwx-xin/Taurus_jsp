<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<% 
   String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta charset="UTF-8">
<title>Ttaurus</title>
<link href="<%=basePath %>js_css/favicon.ico" rel="shortcut icon">
<link href="<%=basePath %>js_css/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="<%=basePath %>js_css/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/chosen/chosen.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/cropper/cropper.min.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/switchery/switchery.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/nouslider/jquery.nouislider.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/ionRangeSlider/ion.rangeSlider.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/plyr/plyr.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/dropzone/basic.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/dropzone/dropzone.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
<link href="<%=basePath %>js_css/contextMenu/css/contextMenu.css" rel="stylesheet">
<link href="<%=basePath %>js_css/my_audio/css/player.css" rel="stylesheet">

<link href="<%=basePath %>js_css/css/animate.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/style.css?v=4.1.0" rel="stylesheet">
<link href="<%=basePath %>js_css/layui/css/layui.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/sudoku.css" rel="stylesheet">
<link href="<%=basePath %>js_css/css/loading.css" rel="stylesheet">
<link href="<%=basePath %>js_css/common/common.css" rel="stylesheet">


<script src="<%=basePath %>js_css/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=basePath %>js_css/common/common.js"></script>
<script src="<%=basePath %>js_css/common/common.file.js"></script>
<script src="<%=basePath %>js_css/common/jquery.contextmenu.r2.js"></script>
<script src="<%=basePath %>js_css/js/jquery.cookie.js"></script>
<script src="<%=basePath %>js_css/js/bootstrap.min.js?v=3.3.6"></script>
<script src="<%=basePath %>js_css/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/layer/layer.min.js"></script>
<script src="<%=basePath %>js_css/js/contabs.js"></script>
<script src="<%=basePath %>js_css/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="<%=basePath %>js_css/js/plugins/jeditable/jquery.jeditable.js"></script>
<script src="<%=basePath %>js_css/js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=basePath %>js_css/layui/layui.js"></script>
<script src="<%=basePath %>js_css/js/plugins/chosen/chosen.jquery.js"></script>
<script src="<%=basePath %>js_css/js/plugins/jsKnob/jquery.knob.js"></script>
<script src="<%=basePath %>js_css/js/plugins/jasny/jasny-bootstrap.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="<%=basePath %>js_css/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
<script src="<%=basePath %>js_css/js/plugins/nouslider/jquery.nouislider.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/switchery/switchery.js"></script>
<script src="<%=basePath %>js_css/js/plugins/ionRangeSlider/ion.rangeSlider.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/iCheck/icheck.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%=basePath %>js_css/js/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/clockpicker/clockpicker.js"></script>
<script src="<%=basePath %>js_css/js/plugins/cropper/cropper.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/validate/messages_zh.min.js"></script>
<script src="<%=basePath %>js_css/js/content.js"></script>
<script src="<%=basePath %>js_css/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/plyr/plyr.js"></script>
<script src="<%=basePath %>js_css/js/plugins/toastr/toastr.min.js"></script>
<script src="<%=basePath %>js_css/js/plugins/dropzone/dropzone.js"></script>
<script src="<%=basePath %>js_css/js/plugins/treeview/bootstrap-treeview.js"></script>
<script src="<%=basePath %>js_css/js/pdfobject/pdfobject.min.js"></script>
<script src="<%=basePath %>js_css/contextMenu/js/jquery.contextMenu.min.js"></script>
<script src="<%=basePath %>js_css/my_audio/js/jquery.marquee.min.js"></script>

<script>

$(function(){
	/* if(isMobile()){
		layer.msg("手机端");
	} else {
		layer.msg("pc端");
	} */
	
	$(document).ajaxComplete(function(event,request, settings){
		var sysErrMessage = request.getResponseHeader("sysErrMessage");
		var redirectUrl = request.getResponseHeader("redirect-url");
		if(isNotNull(redirectUrl)){
			top.location.href="<%=basePath %>"+redirectUrl;
		}
	});
});

//获取登录用户id
function getLoginUserId(){
	return '${userInfo.userId}';
}

function getUserInfoById(userId) {
	var userInfo=null;
	var obj = {};
	obj["userId"] = userId;
	$.ajax({
		url: "<%=basePath %>system/userController/getUserInfoById",
		data: obj,
		dataType: "json",
		type: "post",
		async: false,
		success: function(data){
			if(data.status){
				userInfo = data.objectData;
			}
		},
		error: function(){
		}
	});
	return userInfo;
}

//初始化code select
function initCodeSelect(){
	var codeMast = ${applicationScope.codeMast};
	
	var attrName = "m-code";
	var selectEles = $("select["+attrName+"]");
	if(isNull(codeMast) || selectEles==null || selectEles.length==0){
		return false;
	}
	selectEles.each(function(){
		//获取code的分组
		var codeGroup = $(this).attr(attrName);
		var data = codeMast[codeGroup];
		if(isNull(data)){
			return true;//continue;
		}
		for(var i=0; i<data.length; i++){
			var codeName = data[i].codeName;
			var codeValue = data[i].codeValue;
			$(this).append("<option value='"+codeValue+"'>"+codeName+"</option>");
		};
	});
}

//根据code的值获取名称
function getCodeName(codeGroup,codeValue){
	var codeMast = ${applicationScope.codeMast};
	if(isNull(codeMast)){
		return "";
	}
	
	var codeName = "";
	var codeList = codeMast[codeGroup];
	$.each(codeList, function(index, v){
		if(v.codeValue == codeValue){
			codeName = v.codeName;
			return false;//break;
		}
	});
	return codeName;
}

</script>