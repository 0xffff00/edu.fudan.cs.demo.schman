<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>帐号登录</title>

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
body {background: #444 url(images/low_contrast_linen.png);}

.login-box-wid {
	margin: 0 auto;
	width: 400px;
}

.login-box {
	background: white;
	border-radius: 8px;
	margin: 5px;
	padding: 10px 15px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<div class="container">
		<div class="row login-box-wid">
			<div class="login-box col-sm-12">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<div class="col-sm-10">
						<h2 class="h2">帐号登录</h2>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">账号</label>
						<p class="col-sm-10"><input id="loginbox_username" type="text" class="form-control" placeholder="学号/工号"></p>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<p class="col-sm-10"><input id="loginbox_password" type="password" class="form-control" placeholder="输入密码"></p>
					</div>
					<div class="form-group">
						<p class="col-sm-offset-8 col-sm-4">
						<button type="submit" class="btn btn-primary btn-block">
							<i class="glyphicon glyphicon-user"></i>&nbsp;登录
						</button>
						</p>						
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>