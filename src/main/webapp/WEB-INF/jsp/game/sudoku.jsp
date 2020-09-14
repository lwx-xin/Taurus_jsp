<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<style type="text/css">
	.font-red{
		color: red !important;
	}
</style>
</head>
<body>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-8">
                <div class="ibox float-e-margins" style="padding-left:150px;padding-top:50px;">
					<table cellpadding="0" cellspacing="0" border="0" class="sudoku" id="sudokuTable">
					</table>
				</div>
			</div>
            <div class="col-sm-4">
				<button onclick="drawTable('answer');">answer</button>
				<button onclick="drawTable('question');">question</button>
				<button onclick="createSudoku();">刷新</button>
            </div>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
		createSudoku();
	});
	
	function createSudoku(){
		initData();
		drawTable('question');
		getWhiteBlock();
	}
	
	var question = null;
	var answer = null;
	function initData(){
		$.ajax({
			url: "<%=basePath %>sudokuController/initData",
			data: "",
			dataType: "json",
			type: "post",
			async: false,//false,同步；true,异步
			success: function(data){
				if(data.status){
					question = data.mapData.question;
					answer = data.mapData.answer;
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.msg("err");
			}
		});
	}
	
	function drawTable(type){
		if(type=="question"){
			if(question!=null){
				var html = "";
				$.each(question, function(k, v){
					html += "<tr>";
					$.each(v, function(k1, v1){
						html += template(v1, k1, k);
					});
					html += "</tr>";
				});
				$("table#sudokuTable").html(html);
			}
		} else {
			if(answer!=null){
				$.each(whiteBlock, function(k, v){
					var x = v[0];
					var y = v[1];
					$("td[coordinate-x='"+x+"'][coordinate-y='"+y+"']").find("input[type='text']").addClass("font-red");
					$("td[coordinate-x='"+x+"'][coordinate-y='"+y+"']").find("input[type='text']").val(answer[y][x]);
				});
			}
		}
	}
	
	function template(data, row, line){
		var html = "";
		var sudoku = "sudoku";
		if((line+1)%3==0){
			if((row+1)%3==0){
				sudoku = "sudoku3";
			} else {
				sudoku = "sudoku2";
			}
		} else {
			if((row+1)%3==0){
				sudoku = "sudoku1";
			} else {
				sudoku = "sudoku";
			}
		}
		if(data!="0"){
			html = ""+
			"<td class='"+sudoku+"' coordinate-x='"+row+"' coordinate-y='"+line+"'>"+
			"	<div class='show-num'>"+
			"		<input type='text' value='"+data+"' readonly='true'>"+
			"	</div>"+
			"</td>";
		} else {
			html = ""+
			"<td class='"+sudoku+"' coordinate-x='"+row+"' coordinate-y='"+line+"'>"+
			"	<div class='hidden-num'>"+
			"		<input type='text' maxlength='1' value=''>"+
			"	</div>"+
			"</td>";
		}
		return html;
	}
	
	var whiteBlock = [];
	function getWhiteBlock(){
		whiteBlock = [];
		$("div[class='hidden-num']").each(function(){
			var x = $(this).parent().attr("coordinate-x");
			var y = $(this).parent().attr("coordinate-y");
			whiteBlock.push([x, y]);
		});
	}
</script>
</body>
</html>