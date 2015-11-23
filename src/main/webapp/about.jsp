<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="author" content="School of Computer Science, Fudan University" />
<title>项目说明</title>

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
.h3-k{
	border-bottom: solid 1px #eee
}
.modal-header-sch {
	color:black;
    background-color: white;
 }
.bs-callout-info {
border-left-color: #5bc0de;
}
.bs-callout {
padding: 8px 18px;
margin: 20px 0;
border: 1px solid #eee;
border-left-width: 5px;
border-radius: 3px;
}
.bs-callout i{
color: grey;
}
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
	<div class="conta2iner ">
	<div class="panel panel-default">
	<div class="panel-body">
	<h1>本项目说明</h1>
	<div class="bs-callout bs-callout-info">
	<h3>用途</h3>
	<p>本项目是复旦大学计算机科学技术学院研究生课程 <i>Web软件开发技术</i> 的期末作业。</p>  
	<p>本项目源代码会被公开地托管至<a href="https://github.com/HuangZhenkun/edu.fudan.cs.schapp">GitHub仓库</a>。</p>  
	  <h3>作者(小组成员)</h3>
		  <ul>
		  <li>黄振坤 <i>14210240077@fudan.edu.cn</i></li>
		  <li>杨骁  &nbsp;&nbsp; <i>14210240102@fudan.edu.cn</i></li>
		  <li>易翔宇 <i>14210240104@fudan.edu.cn</i></li>
		  <li>刘敦敏 <i>14210240083@fudan.edu.cn</i></li>
	 	</ul>	   
	</div>
	<div class="bs-callout bs-callout-info">	  
	  <h3>开发中使用的技术</h3>
	   
		  <ul>
		  <li><h4>服务器端</h4>
		  	<ul>
		  		<li><a href="#">Java SE Runtime Environment 8 </a><i>Java 运行时环境</i></li>
		  		<li><a href="http://groovy.codehaus.org">Groovy 2.3 </a><i>基于Java虚拟机的动态脚本语言</i></li>
		  		<li><a href="http://projects.spring.io/spring-framework">Spring Framework 4.1 </a> <i>including Spring Web MVC,Spring DAO</i></li>
		  		<li><a href="http://hsqldb.org">HSQLDB 2.3 </a> <i>轻量级关系型数据库</i></li>
		  	</ul>
		  </li>
		 <li><h4>浏览器端</h4>
		  	<ul>
		  		<li><a href="http://jquery.com">jQuery 1.11.1 </a></li>
		  		<li><a href="http://getbootstrap.com">Bootstrap 3.3 </a></li>
		  	</ul>
		  </li>
	 	</ul>	   
	</div>
	 
	 
	</div>
	</div>
	</div>
</div>
<%@ include file="div-footer.jsp"%>
</body>
</html>