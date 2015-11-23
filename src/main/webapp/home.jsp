<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="author" content="School of Computer Science, Fudan University" />
<title>主页</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/schapp-main.css" type="text/css" />
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>  
    <script src="${pageContext.request.contextPath}/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>

<script src="${pageContext.request.contextPath}/js/common.js"></script>
<style type="text/css">
.modal-header-sch {
	color:black;
    background-color: white;
 }
.jumbotron .role_name{ font-weight: bold;}
</style>
<script type="text/javascript">
$(document).ready(function() {		
	
});

</script>

</head>
<body>

<%@ include file="div-banner.jsp"%>
<div class="schapp-body ">
	<div class="schapp-navi-breadcrumb">
		<ol class="breadcrumb">
			
			
		</ol>
	</div>
	<div class="container ">
	<div class="jumbotron">
	  <h1>${sessionScope.user.username}，欢迎访问!</h1>
	  <p class="text-muted">您的当前角色是<span class="role_name">${sessionScope.user.mainRoleName}</span></p>
	  <p>
<!-- 	  <a class="btn btn-primary btn-lg" href="#" role="button">所有功能说明</a> -->
		<div class="btn-group">
		  <button type="button" class="btn btn-success btn-lg">查看课程表 »</button>
		 
		  
		</div>
	  </p>
	</div>
	</div>
</div>
<%@ include file="div-footer.jsp"%>
</body>
</html>