<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的数据-系统管理</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-dialog.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.dataTables.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/schapp-main.css" type="text/css" />
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>  
    <script src="${pageContext.request.contextPath}/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-dialog.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>

<script src="${pageContext.request.contextPath}/js/common.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
$(document).ready(function() {
	 
	
});

</script>
<style type="text/css">
</style>
</head>
<body>

<div class="dss-body">
  <div class="dss-navi-breadcrumb">	 
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/home">首页</a></li>
		<li><a href="${pageContext.request.contextPath}/mydata">我的数据</a></li>		
		<li class="active">系统管理</li>		
	</ol>
  </div><!--class="dss-navi-breadcrumb"-->  
  <div class="dss-content">
	<ul id="myTab" class="main-tab clearfix tabs">
	  <li class="selected test"><a href="#pane1" class="tab active" data-toggle="tab">用户管理</a></li>
<!-- 	  <li class="test"><a href="#pane2" class="tab" data-toggle="tab">日志记录</a></li> -->
	  <!-- <li class="test"><a href="#pane3" class="tab" data-toggle="tab">创建用户</a></li> -->
	</ul>
	<div id="myTabContent" class="tab-content">
	  <div class="tab-pane fade in active" id="pane1">
	    <table id="users_table" class="cell-border display compact"></table>
	      <div class="below-table">
	        <hr class="dotted">
		      <div class="bottom-right">
			    <button class="dist btn btn-default btn-sm" id="btn_newuser"><span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;新增用户</button>&nbsp;
		      </div><!--  btn btn-info bottom-right-btn -->
          </div><!--class="below-table"--> 
	  </div><!--id="pane1"-->
	  <div class="tab-pane fade in active" id="pane2">
	    <table id="report_table"></table>
	  </div>
	  <div class="tab-pane fade in active" id="pane3">
	    <table id="report_table"></table>
	  </div>
	</div><!--class="tab-content"-->
  </div><!--class="dss-content"-->
</div><!--class="dss-body"-->

</body>
</html>