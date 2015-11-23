package edu.fudan.cs.schapp.controller

import javax.servlet.http.HttpSession

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import edu.fudan.cs.auth.RequirePermission
import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.CourseSelection
import edu.fudan.cs.schapp.model.User
import edu.fudan.cs.schapp.service.CoursesService
import edu.fudan.cs.schapp.service.UsersRolesService



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
		example.numStudents=0
		if (!name)
			return ["error":"name missing"]
		coursesService.createCourse(example)
		["action":"success"]
	}


	@RequestMapping("/course/delete.do")
	public @ResponseBody Object course_delete(Long courseId){
		coursesService.deleteCourse(courseId)
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
	@RequestMapping("/courses/unreserved.json")
	public @ResponseBody Object courses_unreserved(HttpSession session){
		User user=session.getAttribute("user")
		def courses=coursesService.findCoursesUnreserved(user.id)
		[
			"action":"success",
			"recordsTotal":courses.size(),
			"data":courses
		]
	}
	@RequestMapping("/courses/reserved.json")
	public @ResponseBody Object courses_reserved(HttpSession session){
		User user=session.getAttribute("user")
		def courses=coursesService.findCoursesReserved(user.id)
		[
			"action":"success",
			"recordsTotal":courses.size(),
			"data":courses
		]
	}
	@RequestMapping("/course/reserve.do")
	public @ResponseBody Object course_reserve(HttpSession session,Long courseId){
		User user=session.getAttribute("user")
		coursesService.reserveCourse(user.id, courseId)
		["action":"success"]
	}
	@RequestMapping("/course/drop.do")
	public @ResponseBody Object course_drop(HttpSession session,Long courseId){
		User user=session.getAttribute("user")
		coursesService.dropCourse(user.id, courseId)
		["action":"success"]
	}
	@RequestMapping("/courses/taught.json")
	public @ResponseBody Object courses_taught(HttpSession session){
		User user=session.getAttribute("user")
		def courses=coursesService.findCoursesTaught(user.id)
		[
			"action":"success",
			"recordsTotal":courses.size(),
			"data":courses
		]
	}
	@RequestMapping("/courseSelections/learnt.json")
	public @ResponseBody Object courseSelections_learnt(HttpSession session){
		User student=session.getAttribute("user")		
		def rows=coursesService.findCourseSelectionsByStudent(student.id)		
		[
			"action":"success",
			"recordsTotal":rows.size(),
			"data":rows			
		]
	}
	
	@RequestMapping("/courseSelections/query.json")
	public @ResponseBody Object courseSelections_query(Long courseId,Long studentId){
		def rows=[]
		Course course=null
		if (courseId){
			course=coursesService.findCourseById(courseId)
			rows=coursesService.findCourseSelectionsByCourse(courseId)
		}else if (studentId)
			rows=coursesService.findCourseSelectionsByStudent(studentId)

		[
			"action":"success",
			"recordsTotal":rows.size(),
			"data":rows,
			"course":course
		]
	}
	@RequestMapping("/courseSelections/update.do")
	public @ResponseBody Object courseSelections_update(@RequestBody List<Map> changes){
		def examples=[]
		changes.each{
			long id=it['id']
			Double score=it['score']?it['score'].toString().toDouble():null
			String grade=it['grade']
			examples << new CourseSelection(id:id,score:score,grade:grade)
		}
		coursesService.updateCourseSelections(examples)

		["action":"success","info":changes.size()+"条记录已更新"]
	}
}
