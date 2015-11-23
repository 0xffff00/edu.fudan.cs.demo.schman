<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="author" content="School of Computer Science, Fudan University" />

<title>课程管理</title>
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
var rows_data,row;
$(document).ready(function() {
	$("#banner-nav li.student_select_courses").addClass('active');
		
	tryloadData();
});
function tryloadData(){
	
	$.ajax({
 		type:'POST',
 		url:'${pageContext.request.contextPath}/courses/courses/unreserved.json',
 		data:{},
 		success: function(data){
 			if (data.error){
 				bootbox.alert(data.error);
 				return;
 			} 
 			renderCoursesTable(data);
 			
 		}
 	});
	$.ajax({
 		type:'POST',
 		url:'${pageContext.request.contextPath}/courses/courses/reserved.json',
 		data:{},
 		success: function(data){
 			if (data.error){
 				bootbox.alert(data.error);
 				return;
 			} 
 			renderCoursesTable2(data);
 			
 		}
 	});
}
function renderCoursesTable(data){
	$('#courses_table>tbody>tr').remove();
	rows_data=data.data;		 
	var opts= 
		" <button class='sele btn btn-success btn-sm'><i class='glyphicon glyphicon-circle-arrow-right'></i> 选课</button>"

	$.each(rows_data,function(i,row){
		var tea_name=(row.teacher)?row.teacher.username:'';
		var arr=[row.name,row.location,row.credit,tea_name,row.numStudents,opts]		
		$('#courses_table').append(generate_html_tr(arr));
		$('#courses_table button.sele:last').click(function(){
			showReserveCourseConfirmDialog(row.id);
		});		
	});   	
 
}
function renderCoursesTable2(data){
	$('#courses_table2>tbody>tr').remove();
	rows_data=data.data;		 
	var opts= 
		" <button class='unsele btn btn-danger btn-sm'><i class='glyphicon glyphicon-remove'></i> 退选</button>"

	$.each(rows_data,function(i,row){
		var tea_name=(row.teacher)?row.teacher.username:'';
		var arr=[row.name,row.location,row.credit,tea_name,row.numStudents,opts]		
		$('#courses_table2').append(generate_html_tr(arr));
		$('#courses_table2 button.unsele:last').click(function(){
			showDropCourseConfirmDialog(row.id);
		});		
	});   	
 
}

function showReserveCourseConfirmDialog(item_id) {	
	bootbox.confirm("确定要选择该课程吗?", function(result) {
		if (result) {
			tryReserveCourse(item_id);
		}
	});
}
function showDropCourseConfirmDialog(item_id) {	
	bootbox.confirm("确定要退选该课程吗?", function(result) {
		if (result) {
			tryDropCourse(item_id);
		}
	});
}

function tryReserveCourse(courseId) {
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : "${pageContext.request.contextPath}/courses/course/reserve.do",
		data : {"courseId" : courseId},
		success : function(data) {
			if (!data.error) {
				tryloadData();
				Messenger().post("选课成功!");				
			} else {
				bootbox.alert("操作失败: " + data.error);
			}			
		}
		,error : function(xhr,st,err){alert("AJAX ERROR: "+st+err); }
	});
}
function tryDropCourse(courseId) {
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : "${pageContext.request.contextPath}/courses/course/drop.do",
		data : {"courseId" : courseId},
		success : function(data) {
			if (!data.error) {
				tryloadData();
				Messenger().post("退选成功!");				
			} else {
				bootbox.alert("操作失败: " + data.error);
			}			
		}
		,error : function(xhr,st,err){alert("AJAX ERROR: "+st+err); }
	});
}
</script>
</head>
<body>

<%@ include file="div-banner.jsp"%>
<div class="schapp-body container-fluid">
	<div class="schapp-navi-breadcrumb">
		<ol class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}/home">首页</a></li>					
			<li class="active">学生选课</li>
		</ol>
	</div>
	<div class="">
		<div class="col-md-6 col-sm-12 col-xs-12"><div class="panel panel-success ">
			<div class="panel-heading">
				<h3 class="panel-title">可选课程</h3>				
			</div>			
			<div class="panel-body">
				
				<table class="table table-condensed" id="courses_table">
					<thead>
						<tr>							
							<th>课程名称</th>
							<th>上课地点</th>
							<th>学分</th>
							<th>任课教师</th>
							<th>已选人数</th>
							<th>操作</th>
						</tr>
					</thead>
					 
				</table>


			</div>
		</div></div>
		<div class="col-md-6 col-sm-12 col-xs-12"><div class="panel panel-warning ">
			<div class="panel-heading">
				<h3 class="panel-title">已选课程</h3>				
			</div>			
			<div class="panel-body">
				
				<table class="table table-condensed" id="courses_table2">
					<thead>
						<tr>							
							<th>课程名称</th>
							<th>上课地点</th>
							<th>学分</th>
							<th>任课教师</th>
							<th>已选人数</th>
							<th>操作</th>
						</tr>
					</thead>
					 
				</table>


			</div>
		</div></div>
		</div>
</div>
<%@ include file="div-footer.jsp"%>
</body>
</html>