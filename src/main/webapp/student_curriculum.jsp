<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="author" content="School of Computer Science, Fudan University" />

<title>我的课程表</title>
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
	$("#banner-nav li.student_curriculum").addClass('active');
		
	tryloadData();
});
function tryloadData(){
	
	$.ajax({
 		type:'POST',
 		url:'${pageContext.request.contextPath}/courses/courseSelections/learnt.json',
 		data:{},
 		success: function(data){
 			if (data.error){
 				bootbox.alert(data.error);
 				return;
 			} 
 			renderCoursesTable(data);
 			
 		}
 	});
	
}
function renderCoursesTable(data){
	$('#courses_table>tbody>tr').remove();
	rows_data=data.data;		 
	$.each(rows_data,function(i,row){
		var c=row.course;
		var tea_name=c.teacher?c.teacher.username:'';
		var score=row.score?row.score:'';
		var grade=row.grade?row.grade:'';
		var arr=[c.id,c.name,c.location,c.credit,tea_name,score,grade];
		
		$('#courses_table').append(generate_html_tr(arr));
			
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
			<li class="active">我的课程表</li>
		</ol>
	</div>
	<div class="panel panel-success ">
			<div class="panel-heading">
				<h3 class="panel-title"><b>${sessionScope.user.username}</b>的所有课程</h3>				
			</div>			
			<div class="panel-body">
				
				<table class="table table-condensed" id="courses_table">
					<thead>
						<tr>		
							<th>#</th>					
							<th>课程名称</th>
							<th>上课地点</th>
							<th>学分</th>
							<th>任课教师</th>
							<th>成绩分数</th>
							<th>成绩等级</th>
						</tr>
					</thead>
					 
				</table>


			</div>
	</div>
</div>
<%@ include file="div-footer.jsp"%>
</body>
</html>