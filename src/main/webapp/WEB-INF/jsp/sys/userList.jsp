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
<style>
	.wrapper-content{
		padding: 15px;
	}
	
	.link-span{
		color: blue;
		cursor:pointer;
	}
</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>系统用户</h5>
                    </div>
                    <div class="ibox-content">
						<div class="">
                            <a onclick="openAddUser();" href="javascript:void(0);" class="btn btn-primary  display_none">添加用户</a>
                        </div>
                        <table class="table table-striped table-bordered table-hover" id="userDataTable">
                            <thead>
                                <tr>
                                    <th style="display:none;">排序用</th>
                                    <th style="display:none;"></th>
                                    <th>NO</th>
                                    <th>账号</th>
                                    <th>昵称</th>
                                    <th>头像路径</th>
                                    <th>权限名称</th>
                                    <th>禁用 / 启用</th>
                                    <th>注册时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
<script type="text/javascript">

	var userDataTable;
	
	$(function(){
		//渲染datatable
		drawTable();
		
		//加载数据
		initData()
		
	});
	
	function drawTable(){
		userDataTable = $('#userDataTable').DataTable( {
	        "processing": true,      //翻页或者搜索的时候，前端是否给出“正在搜索”等提示信息
	        "serverSide": false,     // true表示使用后台分页 
	        "searching": true,      // 是否允许检索
	        "ordering": true,        // 是否允许排序
	        "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "全部"]],
	        "order": [[ 0, "desc" ]],
	        "columns": [
	            { //排序用
	            	className: "display-none",
	            	"data": null,"render": function (data, type, row, meta) {
	            		var userModifyTime = formatDate(new Date(data.userModifyTime), "yyyy-MM-dd HH:mm:ss");
	            		return "<span name='userModifyTime' style='display:none;'>"+userModifyTime+"</span>";
					}
	            },
	            { 
	            	className: "display-none",
	            	"data": null,"render": function (data, type, row, meta) {
	            		return "<span name='userId' style='display:none;'>"+formatStr(data.userId)+"</span>";
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return "<span name='NO'>1</span>";
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return "<span name='userNumber' class='link-span'>"+formatStr(data.userNumber)+"</span>";
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return formatStr(data.userName);
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return formatStr(data.userHeadPortrait);
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		var authNameList = new Array();
	            		var authList = data.authList;
	            		if(isNotNull(authList)){
	            			$.each(authList, function(index, authObj){
	            				authNameList.push(authObj.authName);
	            			});
	            		}
	            		return authNameList.join();
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		var html = "";
	            		var delFlgJson = '${delFlgJson}';
	            		if(isNotNull(delFlgJson)){
		            		var userDelFlg = JSON.parse(delFlgJson);
		            		var code = formatStr(data.userDelFlg);
		            		html += userDelFlg[code];
	            		}
	            		return html;
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return formatDate(new Date(data.userCreateTime), "yyyy-MM-dd HH:mm:ss");
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return "<a href='#' onclick='openUpdateUser(this);'><i class='fa fa-edit text-navy'> 编辑</i></a>";
					}
	            }
	        ],
            "createdRow": function (row, data, dataIndex) {
            	$(row).find("td.display-none").hide();
            },
	    } );
	}
	
	function initData(){
		$.ajax({
			url: "<%=basePath %>system/userController/initUserList",
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
					userDataTable.clear();
					userDataTable.rows.add(data.listData);
					userDataTable.draw();
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("err");
			}
		});
	}
	
	function openAddUser(){
		layer.open({
			  type: 2,
			  title: "添加用户",
			  area: ['800px', '500px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: '<%=basePath %>system/userController/openEditUser'
		});
	}
	
	function openUpdateUser(ele){
		var userId = $(ele).parents("tr").find("span[name='userId']").html();
		layer.open({
			  type: 2,
			  title: "编辑用户",
			  area: ['800px', '500px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: '<%=basePath %>system/userController/openEditUser?userId='+userId
		});
	}
	
</script>
</html>