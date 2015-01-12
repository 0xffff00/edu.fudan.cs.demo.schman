<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="auth" uri="WEB-INF/auth.tld"%>

<script type="text/javascript">
	$(document).ready(function() {
		// 	$("#btnLogoff").click(tryLogoff);
		// 	$("#btn_user_info_save").click(trySaveCurrentUserInfo);
		// 	$("#curr_date").html(DateUtils.format(new Date(),DateUtils.ISO8601_DATE));	

	});
</script>
<div class="schapp-banner">

	<ul id="banner-nav" class="nav nav-tabs" role="tablist">
		<li class="manage_students"><a href="${pageContext.request.contextPath}/manage/students">学生信息管理</a></li>
		<li class="manage_teachers"><a href="${pageContext.request.contextPath}/manage/teachers">教师信息管理</a></li>
		<li class="manage_courses"><a href="${pageContext.request.contextPath}/manage/courses">课程信息管理</a></li>
		<li class="student_select_courses"><a href="${pageContext.request.contextPath}/student/select_courses">选课</a></li>
		<li role="presentation" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> Dropdown <span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li>aa</li>
			</ul>
			</li>

	</ul>
</div>
<div id="tipbox0" class="alert alert-warning" role="alert"></div>
<div id="tipbox-wrapper">
<div id="tipbox" ></div>
</div>

