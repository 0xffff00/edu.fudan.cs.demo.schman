<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="auth" uri="WEB-INF/auth.tld"%>

<script type="text/javascript">
$(document).ready(function() {
	 	$("#topbar-btn-logoff").click(tryLogoff);
	// 	$("#btn_user_info_save").click(trySaveCurrentUserInfo);
	// 	$("#curr_date").html(DateUtils.format(new Date(),DateUtils.ISO8601_DATE));	

});
function tryLogoff(){
	$.ajax({
		type : "GET",	
		dataType : 'json',
		url : "${pageContext.request.contextPath}/auth/logoff.do",	
		
		success : function(data) {			
			if (!data.error)
				window.location.reload();
			else
				alert("注销失败，请重试！");
		}
	});
}
</script>
<div class="schapp-topbar">

<nav class="navbar  navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#schapp-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
     
      <a class="navbar-brand navbar-brand-schapp" href="${pageContext.request.contextPath}/home">
       <img height="30px" src="${pageContext.request.contextPath}/images/fudan.png" />
      <span>学籍管理系统</span>
      </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="schapp-navbar-collapse-1">
		<ul id="banner-nav" class="nav navbar-nav">
			<auth:require roles="admin">
			<li role="presentation" class="dropdown manage_users">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 人员管理 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li class="manage_students"><a href="${pageContext.request.contextPath}/manage/students">学生信息管理</a></li>
					<li class="manage_teachers"><a href="${pageContext.request.contextPath}/manage/teachers">教师信息管理</a></li>
				</ul>
			</li>
			<li class="manage_courses"><a href="${pageContext.request.contextPath}/manage/courses">课程信息管理</a></li>
			</auth:require>
			<auth:require roles="student,admin">
			<li class="student_select_courses"><a href="${pageContext.request.contextPath}/student/select_courses">选课</a></li>
			<li class="student_curriculum"><a href="${pageContext.request.contextPath}/student/curriculum">成绩查询</a></li>
			</auth:require>
			<auth:require roles="teacher,admin">
			<li class="teacher_grade"><a href="${pageContext.request.contextPath}/teacher/grade">成绩评定</a></li>
			</auth:require>
		</ul>
		<ul class="nav navbar-nav navbar-right">    
		  <auth:require>   
		  <li class="dropdown">
		    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		    	<span id="topbar-username">${sessionScope.user.username} </span><span class="caret"></span>
		    </a>
		    <ul class="dropdown-menu" role="menu">
		<!--             <li><a href="#">Action</a></li> -->
		<!--             <li class="divider"></li> -->
		      <li><a href="#" id="topbar-btn-logoff">注销</a></li>
		    </ul>
		  </li>
		  </auth:require>
		</ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>
<div class="schapp-banner">


</div>
<div id="tipbox0" class="alert alert-warning" role="alert"></div>
<div id="tipbox-wrapper">
<div id="tipbox" ></div>
</div>

