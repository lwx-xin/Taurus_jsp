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
                                <label class="col-sm-3 control-label">头像：</label>
                                <div class="col-sm-8">
	                                <input name="userHeadPortrait" type="file" class="form-control" style="display: none;" aria-required="true">
								</div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">昵称：</label>
                                <div class="col-sm-8">
                                    <input id="userName" name="userName" class="form-control" type="text" aria-required="true">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 最多15个文字</span>
                                </div>
                            </div>
                            <div class="form-group" name="pwdDiv">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-8">
                                    <input id="userPwd" autocomplete="off" name="userPwd" class="form-control password-disc" type="text" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group" name="pwdDiv">
                                <label class="col-sm-3 control-label">确认密码：</label>
                                <div class="col-sm-8">
                                    <input id="password1" autocomplete="off" name="password1" class="form-control password-disc" type="text" aria-required="true">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> <span id="pwdMsg">请再次输入您的密码</span></span>
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-3 control-label">权限：</label>
                                <div class="col-sm-8">
	                                <select id="authId" name="authId" data-placeholder="请选择权限..." class="chosen-select " style="width:490px;" tabindex="2" aria-required="true" multiple>
	                                    <option value="">请选择权限</option>
	                                </select>
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-3 control-label" >禁用：</label>
                                <div class="col-sm-8">
	                                <input type="checkbox" id="userDelFlg" name="userDelFlg"/>&nbsp;&nbsp;
	                                <span class="m-b-none"><span name="userDelFlg_msg">账号已禁用</span></span>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="button" onclick="editInfo();">提交</button>
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

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var userDelFlgEle;
	$(function(){

		//渲染控件
		drawElement();
		
		initData();
	})
	
	//渲染控件
	function drawElement(){
		//初始化权限下拉框
		var allAuthJson = '${allAuthJson}';
		if(isNotNull(allAuthJson)){
			var allAuth = JSON.parse(allAuthJson);
			$.each(allAuth, function(index, value){
				//名称
				var name = value.authName;
				//权限级别
				//var level = value.authLevel;
				//权限id
				var authId = value.authId;
				
				$("#authId").append("<option value='"+authId+"'>"+name+"</option>");
			});
		}
		$(".chosen-select").chosen();
		
		$('input[type="file"]' ).prettyFile();

        userDelFlgEle = new Switchery(document.getElementById("userDelFlg") , {
            color: '#1AB394'
        });
        
        $("#userDelFlg").change(function(){
        	if($("#userDelFlg").is(":checked")){
        		$("span[name='userDelFlg_msg']").html("账号已启用");
        	} else {
        		$("span[name='userDelFlg_msg']").html("账号已禁用");
        	}
        });
        
        $("#userPwd,#password1").on("input", function(){
        	var pwd = $("#userPwd").val();
        	var pwd1 = $("#password1").val();
        	if(isNull(pwd) || isNull(pwd1)){
        		$("#pwdMsg").html("请再次输入您的密码");
        	} else if(pwd!=pwd1){
        		$("#pwdMsg").html("<span style='color:red'>两次密码不一致</span>");
        	} else {
        		$("#pwdMsg").html("");
        	}
        });
	}
	
	function initData(){
		var userId = '${userId}';
		
		if(isNotNull(userId)){
			//编辑用户
			$("div[name='pwdDiv']").hide();
		} else {
			//新增用户
			$("div[name='pwdDiv']").show();
			return false;
		}
		
		var obj = {};
		obj["userId"] = userId;
		$.ajax({
			url: "<%=basePath %>system/userController/initEditUser",
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
					$("#userName").val(data.objectData.userName);
					$("#userPwd").val(data.objectData.userPwd);
					var authList = data.objectData.authList;
            		if(isNotNull(authList)){
            			$.each(authList, function(index, authObj){
            				$("#authId option[value='"+authObj.authId+"']").attr("selected","selected");
            			});
    					$("#authId").trigger("chosen:updated");
    					$("#authId").chosen();
            		}
					if(data.objectData.userDelFlg=="0"){
						userDelFlgEle.setPosition(false);
						userDelFlgEle.handleOnchange(false);
					} else {
						userDelFlgEle.setPosition(true);
						userDelFlgEle.handleOnchange(true);
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
	
	function editInfo(){
		var param = {};
		param["userId"] = '${userId}';
		param["userName"] = $("#userName").val();
		param["userPwd"] = $("#userPwd").val();
		param["userHeadPortrait"] = $("[name='userHeadPortrait']").val();
		if($("#userDelFlg").is(":checked")){
			param["userDelFlg"] = "1";
		} else {
			param["userDelFlg"] = "0";
		}
		
		//权限list
		var authList = new Array();
		var authIdList = $("#authId").val();
		if(isNotNull(authIdList)){
			$.each(authIdList, function(index, value){
				authList.push({"authId":value});
			});
		}
		param["authList"] = authList;
		console.log(param);
		
		$.ajax({
			url: "<%=basePath %>system/userController/editUserDetail",
			data: JSON.stringify(param),
			contentType: "application/json",
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
						//如果修改的是当前登录用户，就需要重新登录
						if('${userId}' == getLoginUserId()){
							top.location.href = "<%=basePath %>webLogout";
						} else {
							closeFrame();
						}
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
		//parent.layer.close(index);//关闭当前dialog
		parent.location.reload();//重新加载父页面
	}
	
</script>
</html>