package edu.fudan.cs.schapp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User
import edu.fudan.cs.schapp.service.CoursesService
import edu.fudan.cs.schapp.service.UsersRolesService
import edu.fudan.cs.auth.RequirePermission

 

/**
 * 基于角色的访问控制(RBAC) 系统管理模块 AJAX数据交互API
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014年12月15日
 * @since groovy 2.3
 */
@RequirePermission("schapp.courses")
@Controller
@RequestMapping("/courses")
class CoursesController {

	@Autowired
	CoursesService coursesService
	@Autowired
	UsersRolesService usersRolesService

	@RequestMapping("/course/update.do")
	public @ResponseBody Object course_update(String code,String name,String location,Long teacher_id,Integer credit,Long id){
		User teacher=usersRolesService.findUserById(teacher_id)
		Course example=new Course(code:code,name:name,location:location,teacher:teacher,credit:credit)		 
		example.id=id
		if (!id)
			return ["error":"id missing"]
		if (!name)
			return ["error":"name missing"]		
		coursesService.updateCourse(example)
		["action":"success"]
	}

	@RequestMapping("/course/create.do")
	public @ResponseBody Object course_create(String code,String name,String location,Long teacher_id,Integer credit){
		User teacher=teacher_id?usersRolesService.findUserById(teacher_id):null
		if (!credit) credit=0
		Course example=new Course(code:code,name:name,location:location,teacher:teacher,credit:credit)	
		
		if (!name)
			return ["error":"name missing"]		
		coursesService.createCourse(example)
		["action":"success"]
	}
	

	@RequestMapping("/course/delete.do")
	public @ResponseBody Object course_delete(Long id){
		coursesService.deleteCourse(id)		
		["action":"success"]
	}

	
	@RequestMapping("/courses/all.json")
	public @ResponseBody Object courses_all(){
		def courses=coursesService.findCourses()

		[
			"action":"success",
			"recordsTotal":courses.size(),
			"data":courses
		]
	}
}
