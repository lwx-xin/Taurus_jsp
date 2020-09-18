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
                                <label class="col-sm-3 control-label">请求路径：</label>
                                <div class="col-sm-8">
                                    <input id="urlPath" name="urlPath" class="form-control" type="text" aria-required="true">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 最多100个文字</span>
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-3 control-label">请求方式：</label>
                                <div class="col-sm-8">
	                                <select id="urlMethod" name="urlMethod" data-placeholder="请选择请求方式..." class="chosen-select " style="width:490px;" tabindex="2" aria-required="true" multiple>
	                                    <option value="">请选择请求方式</option>
	                                    <option value="get" hassubinfo="true">get</option>
	                                    <option value="post" hassubinfo="true">post</option>
	                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">请求备注：</label>
                                <div class="col-sm-8">
                                    <input id="urlRemarks" name="urlRemarks" class="form-control" type="text" aria-required="true">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 最多150个文字</span>
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
	                                <input type="checkbox" id="urlDelFlg" name="urlDelFlg"/>&nbsp;&nbsp;
	                                <span class="m-b-none"><span name="urlDelFlg_msg">请求已禁用</span></span>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="button" onclick="updateInfo();">提交</button>
                                </div>
                            </div>
                            
                            <input type="hidden" name="authUrlId" id="authUrlId" />
                        </form>
                    </div>
                </div>
            </div>
		</div>
	</div>
</body>
<script type="text/javascript">

	var urlDelFlgEle;
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

        urlDelFlgEle = new Switchery(document.getElementById("urlDelFlg") , {
            color: '#1AB394'
        });
        
        $("#urlDelFlg").change(function(){
        	if($("#urlDelFlg").is(":checked")){
        		$("span[name='urlDelFlg_msg']").html("请求已启用");
        	} else {
        		$("span[name='urlDelFlg_msg']").html("请求已停用");
        	}
        });
	}
	
	function initData(){
		var urlId = '${urlId}';
		if(isNull(urlId)){
			//新增请求
			return false;
		} else {
			//编辑请求
		}
		
		var obj = {};
		obj["urlId"] = urlId;
		$.ajax({
			url: "<%=basePath %>system/urlController/initEditUrl",
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
					//请求路径
					var urlPath = data.objectData.urlPath;
					//请求方法get，post
					var urlMethod = data.objectData.urlMethod;
					//备注
					var urlRemarks = data.objectData.urlRemarks;
					//权限列表
					var authList = data.objectData.authList;
					//禁用状态
					var urlDelFlg = data.objectData.urlDelFlg;
					
					//请求路径
					$("#urlPath").val(urlPath);
					//请求方法
					if(isNotNull(urlMethod)){
						var urlMethodList = JSON.parse(urlMethod);
            			$.each(urlMethodList, function(index, method){
            				$("#urlMethod option[value='"+method+"']").attr("selected","selected");
            			});
    					$("#urlMethod").trigger("chosen:updated");
    					$("#urlMethod").chosen();
					}
					//备注
					$("#urlRemarks").val(urlRemarks);
					//权限列表
            		if(isNotNull(authList)){
            			$.each(authList, function(index, authObj){
            				$("#authId option[value='"+authObj.authId+"']").attr("selected","selected");
            			});
    					$("#authId").trigger("chosen:updated");
    					$("#authId").chosen();
            		}
					//状态
					if(urlDelFlg=="0"){
						urlDelFlgEle.setPosition(false);
						urlDelFlgEle.handleOnchange(false);
					} else {
						urlDelFlgEle.setPosition(true);
						urlDelFlgEle.handleOnchange(true);
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
	
	function updateInfo(){
		var param = {};
		param["urlId"] = "${urlId}";
		//请求路径
		param["urlPath"] = $("#urlPath").val();
		//请求方法post,get
		param["urlMethod"] = JSON.stringify($("#urlMethod").val());
		//备注
		param["urlRemarks"] = $("#urlRemarks").val();
		//禁用状态
		if($("#urlDelFlg").is(":checked")){
			param["urlDelFlg"] = "1";
		} else {
			param["urlDelFlg"] = "0";
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
		
		console.log(JSON.stringify(param))
		
		$.ajax({
			url: "<%=basePath %>system/urlController/editUrlDetail",
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