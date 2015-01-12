<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="author" content="School of Computer Science, Fudan University" />

<title>当前课程成绩评定</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messenger.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messenger-theme-flat.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/schapp-main.css" type="text/css" />
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>  
    <script src="${pageContext.request.contextPath}/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>

<%-- <script src="${pageContext.request.contextPath}/js/bootstrap3-typeahead.min.js"></script> --%>

<script src="${pageContext.request.contextPath}/js/messenger.js"></script>
<script src="${pageContext.request.contextPath}/js/messenger-theme-flat.js"></script>


<script src="${pageContext.request.contextPath}/js/common.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
var rows_data,row,courseId,course;
$(document).ready(function() {
	$("#banner-nav li.teacher_grade").addClass('active');
	courseId=getUrlRestfulParam('course');
	$("#btn_reset").click(function(){
		$("#stus_table input.cs_score").val('');
	});
	$("#btn_calc").click(function(){
		$('#stus_table>tbody>tr').each(function(i,n){
			var score=$(n).find('.cs_score').val();
			var gp=calcGP(score);
			$(n).find('.cs_grade').html(gp.grade);
		});
	});
	$("#btn_save").click(trySaveData);
	tryloadData();
});
function tryloadData(){
	
	$.ajax({
 		type:'POST',
 		url:'${pageContext.request.contextPath}/courses/courseSelections/query.json',
 		data:{"courseId":courseId},
 		success: function(data){
 			if (data.error){
 				bootbox.alert(data.error);
 				return;
 			} 
 			if (data.course){
 				course=data.course;
 				$(".course_name").html('<b>'+course.name+'</b> 授课学生名单');
 			}
 			renderStusTable(data);
 			
 		}
 	});
	
}
function renderStusTable(data){
	$('#stus_table>tbody>tr').remove();
	rows_data=data.data;		 
	$.each(rows_data,function(i,row){
		var s=row.student;
		var arr=[s.id,s.username,s.department];
		var s1=row.score? row.score:'';
		var s2=row.grade? row.grade:'';
		arr.push('<input class="cs_score" value="'+s1+'" />')
		arr.push('<span class="cs_grade">'+s2+'</span>')
		$('#stus_table').append(generate_html_tr(arr));
			
	});   	
 
}
function trySaveData(){
	var post_data=[];
	$('#stus_table>tbody>tr').each(function(i,n){
		console.log(n)
		var _id=$(n).find('tr:first').html();
		var _score=$(n).find('.cs_score').val();
		var _grade=$(n).find('.cs_grade').html();
		post_data.push({id:rows_data[i].id,score:_score,grade:_grade});
	});
	$.ajax({
 		type:'POST',
 		dataType : 'json',
 		url:'${pageContext.request.contextPath}/courses/courseSelections/update.do',
 		contentType:'application/json',	
 		data:JSON.stringify(post_data),
 		success: function(data){ 			
 			if (data.error){
 				bootbox.alert(data.error);
 				return;
 			} 
 			if (data.info) Messenger().post(data.info);	
 		}
 	});
}
var GP_LIST=[
   	[90,'A',4.0],
   	[85,'A-',3.7],
   	[82,'B+',3.3],
   	[78,'B',3.0],
   	[75,'B-',2.7],
   	[72,'C+',2.3],
   	[68,'C',2.0],
   	[64,'C-',1.5],
   	[60,'D',1.0],
   	[0,'F',0]];
function calcGP(score){
	var m=GP_LIST;
	if (!score) return {};
	for (var i=0;i<m.length;i++){
		if (score>=m[i][0]) return {gp:m[i][2],grade:m[i][1]};
	}
	return {};
}
</script>
</head>
<body>

<%@ include file="div-banner.jsp"%>
<div class="schapp-body">
		<div class="schapp-navi-breadcrumb">
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/home">首页</a></li>	
				<li><a href="${pageContext.request.contextPath}/teacher/grade">教学任务课程列表</a></li>				
				<li class="active">当前课程成绩评定</li>
			</ol>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title course_name">当前课程</h3>				
			</div>			
			<div class="panel-body">				
				<table class="table table-condensed" id="stus_table">
					<thead>
						<tr>
							<th>学号</th>
							<th>学生姓名</th>
							<th>所在院系</th>
							<th>成绩分数</th>
							<th>成绩等级</th>
						
						</tr>
					</thead>
					 
				</table>
			
			
			<hr class="dotted" />
			<div class="333">	
				<button type="button" class="btn btn-success" id="btn_save"><span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;保存</button>&nbsp;
				<button type="button" class="btn btn-info" id="btn_calc"><span class="glyphicon glyphicon-calendar"></span>&nbsp;计算成绩等级</button>&nbsp;
				<button type="button" class="btn btn-default" id="btn_reset"><span class="glyphicon glyphicon-repeat"></span>&nbsp;重置</button>&nbsp;
			</div>
		
			
		</div>
		
	</div>
</div>
</body>
</html>