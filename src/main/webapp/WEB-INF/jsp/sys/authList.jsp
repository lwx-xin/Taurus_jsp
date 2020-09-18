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
                        <h5>权限管理</h5>
                    </div>
                    <div class="ibox-content">
						<div class="">
                            <a onclick="openAddAuth();" href="javascript:void(0);" class="btn btn-primary  display_none">添加用户</a>
                        </div>
                        <table class="table table-striped table-bordered table-hover" id="authDataTable">
                            <thead>
                                <tr>
                                    <th style="display:none;"></th>
                                    <th>NO</th>
                                    <th>权限级别</th>
                                    <th>权限名称</th>
                                    <th>禁用 / 启用</th>
                                    <th>创建时间</th>
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

	var authDataTable;
	
	$(function(){
		//渲染datatable
		drawTable();
		
		//加载数据
		initData()
		
	});
	
	function drawTable(){
		authDataTable = $('#authDataTable').DataTable( {
	        "processing": true,      //翻页或者搜索的时候，前端是否给出“正在搜索”等提示信息
	        "serverSide": false,     // true表示使用后台分页 
	        "searching": true,      // 是否允许检索
	        "ordering": false,        // 是否允许排序
	        "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "全部"]],
	        "columns": [
	            { 
	            	className: "display-none",
	            	"data": null,"render": function (data, type, row, meta) {
	            		return "<span name='authId'>"+formatStr(data.authId)+"</span>";
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		//var rowNumber = meta.row + 1 + meta.settings._iDisplayStart;
	            		return "<span name='NO'>1</span>";
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return formatStr(data.authLevel);
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return formatStr(data.authName);
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		var html = "";
	            		var delFlgJson = '${delFlgJson}';
	            		if(isNotNull(delFlgJson)){
		            		var delFlg = JSON.parse(delFlgJson);
		            		var code = formatStr(data.authDelFlg);
		            		html += delFlg[code];
	            		}
	            		return html;
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return formatDate(new Date(data.authCreateTime), "yyyy-MM-dd HH:mm:ss");
					}
	            },
	            { 
	            	"data": null,"render": function (data, type, row, meta) {
	            		return "<a href='#' onclick='openUpdateAuth(this);'><i class='fa fa-edit text-navy'> 编辑</i></a>";
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
			url: "<%=basePath %>system/authController/initAuthList",
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
					authDataTable.clear();
					authDataTable.rows.add(data.listData);
					authDataTable.draw();
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("err");
			}
		});
	}
	
	function openAddAuth(){
		layer.open({
			  type: 2,
			  title: "添加权限",
			  area: ['800px', '500px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: '<%=basePath %>system/authController/openEditAuth'
		});
	}
	
	function openUpdateAuth(ele){
		var authId = $(ele).parents("tr").find("span[name='authId']").html();
		layer.open({
			  type: 2,
			  title: "编辑权限",
			  area: ['800px', '500px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: '<%=basePath %>system/authController/openEditAuth?authId='+authId
		});
	}
	
</script>
</html>