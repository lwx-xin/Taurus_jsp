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
                                    <input id="authName" name="authName" class="form-control" type="text" aria-required="true">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 最多100个文字</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">权限级别：</label>
                                <div class="col-sm-8">
                                    <input id="authLevel" name="authLevel" class="form-control" type="text" aria-required="true">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 最多100个文字</span>
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-3 control-label" >禁用：</label>
                                <div class="col-sm-8">
	                                <input type="checkbox" id="authDelFlg" name="authDelFlg"/>&nbsp;&nbsp;
	                                <span class="m-b-none"><span name="delFlg_msg">权限已禁用</span></span>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="button" onclick="updateInfo();">提交</button>
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

	var authDelFlgEle;
	$(function(){

		//渲染控件
		drawElement();
		
		initData();
	})
	
	//渲染控件
	function drawElement(){
		//初始化权限下拉框
        authDelFlgEle = new Switchery(document.getElementById("authDelFlg") , {
            color: '#1AB394'
        });
        
        $("#authDelFlg").change(function(){
        	if($("#authDelFlg").is(":checked")){
        		$("span[name='delFlg_msg']").html("权限已启用");
        	} else {
        		$("span[name='delFlg_msg']").html("权限已停用");
        	}
        });
	}
	
	function initData(){
		var authId = '${authId}';
		if(isNull(authId)){
			//新增权限
			return false;
		} else {
			//编辑权限
		}
		
		var obj = {};
		obj["authId"] = authId;
		$.ajax({
			url: "<%=basePath %>system/authController/initEditAuth",
			data: obj,
			dataType: "json",
			type: "post",
			async: false,//false,同步；true,异步
			success: function(data){
				if(data.status){
					//名称
					var authName = data.objectData.authName;
					//权限级别
					var authLevel = data.objectData.authLevel;
					//禁用状态
					var authDelFlg = data.objectData.authDelFlg;

					$("#authName").val(authName);
					$("#authLevel").val(authLevel);
					if(authDelFlg=="0"){
						authDelFlgEle.setPosition(false);
						authDelFlgEle.handleOnchange(false);
					} else {
						authDelFlgEle.setPosition(true);
						authDelFlgEle.handleOnchange(true);
					}
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("ajax err");
			}
		});
	}
	
	//check权限是否有用户，请求正在使用
	function updateCheck(){
		var message="";
		var obj = {};
		obj["authId"] = "${authId}";
		$.ajax({
			url: "<%=basePath %>system/authController/editUrlDetailCheck",
			data: obj,
			dataType: "json",
			type: "post",
			async: false,//false,同步；true,异步
			success: function(data){
				if(!data.status){
					message = "有1个用户，2个请求正在使用该权限，请谨慎操作！";
					//layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("ajax err");
			}
		});
		return message;
	}
	
	function updateInfo(){
		//check权限是否有用户或者请求正在使用
		var checkMsg = updateCheck();
		if(isNull(checkMsg)){
			editAjax();
		} else {
			swal({
				title: "您确定要编辑这条信息吗",
				text: checkMsg,
				icon: "warning",
				buttons: true,
				dangerMode: true,
			})
			.then((willDelete) => {
				if (willDelete) {
					editAjax();
				} else {
					swal("您取消了编辑操作！", {icon: "error",})
				}
			});
		}
	}
	
	function editAjax(){
		var param = {};
		param["authId"] = "${authId}";
		//名称
		param["authName"] = $("#authName").val();
		//权限级别
		param["authLevel"] = $("#authLevel").val();
		//禁用状态
		if($("#authDelFlg").is(":checked")){
			param["authDelFlg"] = "1";
		} else {
			param["authDelFlg"] = "0";
		}
		
		$.ajax({
			url: "<%=basePath %>system/authController/editAuthDetail",
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: "json",
			type: "post",
			async: false,//false,同步；true,异步
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