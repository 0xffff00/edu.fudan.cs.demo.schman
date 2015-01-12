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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/select2.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/select2-bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/schapp-main.css" type="text/css" />
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>  
    <script src="${pageContext.request.contextPath}/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>

<%-- <script src="${pageContext.request.contextPath}/js/bootstrap3-typeahead.min.js"></script> --%>

<script src="${pageContext.request.contextPath}/js/messenger.js"></script>
<script src="${pageContext.request.contextPath}/js/messenger-theme-flat.js"></script>


<script src="${pageContext.request.contextPath}/js/common.js"></script>
<style type="text/css">
.typeahead { z-index: 1051;}
</style>
<script type="text/javascript">
var rows_data,row;
$(document).ready(function() {
	$("#banner-nav li.manage_courses").addClass('active');
	$("#btn_user_add").click(function(){
		showEditModal(0);
	});
	$("#btn_user_save").click(trySaveCourse);
	$.ajax({
 		type:'GET',
 		url:'${pageContext.request.contextPath}/sys/users/teachers/list2.json', 		
 		success: function(data){ 			
 			 //$('#course_edit_modal input[name="teacher_name"]').typeahead({source:data});
 			 //$('#sss').select2({data:data,tags:true});
 			var $select= $('#course_edit_modal select[name="teacher_id"]');
 			$.each(data,function(i,n){	$select.append(new Option(n.text,n.id));}); 			
 			$select.selectpicker();
 			
 		}
 	});
	
	tryloadData();
});
function tryloadData(){
	
	$.ajax({
 		type:'POST',
 		url:'${pageContext.request.contextPath}/courses/courses/all.json',
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
	var opts= 
		" <button class='edit btn btn-default btn-sm'><i class='glyphicon glyphicon-edit'></i> 编辑</button>"
    	+" <button class='dele btn btn-default btn-sm'><i class='glyphicon glyphicon-trash'></i> 删除</button>"
	$.each(rows_data,function(i,row){
		var tea_name=(row.teacher)?row.teacher.username:'';
		var arr=[row.id,row.name,row.location,row.credit,tea_name,opts]		
		$('#courses_table').append(generate_html_tr(arr));
		$('#courses_table button.edit:last').click(function(){
			showEditModal(row.id);
		});
		$('#courses_table button.dele:last').click(function(){
			showDeleteItemConfirmDialog(row.id);
		});
	});
    	
 
}
function showEditModal(row_id){	
	 if (row_id){
		 $('#course_edit_modal form input[name]').val('');
		 var row=getRowDataById(rows_data,row_id);
		 $('#course_edit_modal form').fillForm(row);		
	 	$('#course_edit_modal form select[name="teacher_id"]').selectpicker('val',row.teacher && row.teacher.id);
	 	
	 }else{
		 $('#course_edit_modal form input[name]').val('');
		 $('#course_edit_modal form select[name="teacher_id"]').selectpicker('val','');
		
	 }	
	 
	 $('#course_edit_modal').modal('show');
}

function showDeleteItemConfirmDialog(item_id) {	
	bootbox.confirm("确定要删除该条目吗?", function(result) {
		if (result) {
			tryDeleteCourse(item_id);
		}
	});
}
function tryDeleteCourse(uid) {
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : "${pageContext.request.contextPath}/courses/course/delete.do",
		data : {"userId" : uid},
		success : function(data) {
			if (!data.error) {
				tryloadData();
				Messenger().post("删除成功!");				
			} else {
				bootbox.alert("操作失败: " + data.error);
			}
			$('#course_edit_modal').modal('hide');
		}
		,error : function(xhr,st,err){alert("AJAX ERROR: "+st+err); }
	});
}

function trySaveCourse() {	
	var post_data = $('#course_edit_modal').readForm();	
	var tea_id=$('#course_edit_modal select[name="teacher_id"]').val();
	if (tea_id) post_data['teacher_id']=tea_id;
	var url = post_data['id'] ? "${pageContext.request.contextPath}/courses/course/update.do"
			: "${pageContext.request.contextPath}/courses/course/create.do";
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : url,
		data : post_data,
		success : function(data) {
			if (!data.error) {
				tryloadData();				
				Messenger().post("保存成功!");
				$('#course_edit_modal').modal('hide');
			} else {
				bootbox.alert("操作失败: " + data.error);
			}			
		}
	});
}
</script>
</head>
<body>

	<%@ include file="div-banner.jsp"%>
	<div class="schapp-body">
		<div class="schapp-navi-breadcrumb">
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/home">首页</a></li>					
				<li class="active">课程管理</li>
			</ol>
		</div>

		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">课程管理</h3>				
			</div>			
			<div class="panel-body">
				<div class="bottom-right">
					<button type="button" class="btn btn-default bottom-left-btn" id="btn_user_add">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增课程
					</button>				 		
				</div>
				<table class="table table-condensed" id="courses_table">
					<thead>
						<tr>
							<th>#</th>
							<th>课程名称</th>
							<th>上课地点</th>
							<th>学分</th>
							<th>任课教师</th>
							<th>操作</th>
						</tr>
					</thead>
					 
				</table>


			</div>
		</div>
	</div>

<div id="course_edit_modal" class="modal" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header modal-header-sch">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">课程信息</h4>
      </div>
      <div class="modal-body">
      	<form class="form-horizontal" role="form">      	
			<div class="form-group">
				<label class="col-sm-2 control-label">名称*</label>
				<p class="col-sm-10"><input name="name" class=" form-control" placeholder="名称"></p>
			</div>		
			<div class="form-group">
				<label class="col-sm-2 control-label">上课地点</label>
				<p class="col-sm-10"><input name="location" class="form-control" placeholder="上课地点"></p>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">学分</label>
				<p class="col-sm-10"><input name="credit" class="form-control" placeholder="学分"></p>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">任课教师</label>
				
				<p class="col-sm-10">
					<select name="teacher_id" class="form-control" data-live-search="true" placeholder="任课教师">
						
					</select>
				</p>
			</div>
			
			<input name="id" type="hidden">
			
		</form>     
      </div><!-- /.modal-body -->
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="btn_user_save" ><i class="glyphicon glyphicon-floppy-disk"></i> 保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div><!-- /.modal-footer -->
    </div><!-- /.modhal-content -->
  </div><!-- /.modal-dialog -->
 </div>
 <div id="aaa"></div>
</body>
</html>