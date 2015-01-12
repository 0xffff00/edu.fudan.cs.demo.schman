package edu.fudan.cs.schapp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User
import edu.fudan.cs.schapp.service.UsersRolesService
import edu.fudan.cs.auth.RequirePermission

 

/**
 * 基于角色的访问控制(RBAC) 系统管理模块 AJAX数据交互API
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014年12月15日
 * @since groovy 2.3
 */
@RequirePermission("schapp.sysman")
@Controller
@RequestMapping("/sys")
class SysManController {

	@Autowired
	UsersRolesService usersRolesService


	@RequestMapping("/user/update.do")
	public @ResponseBody Object user_update(String code,String username,String password,String main_role_name,String department,String major,Long id){
		Role main_role=usersRolesService.findRoleByName(main_role_name)
		User example=new User(code:code,username:username,password:password,main_role:main_role,department:department,major:major)
		example.id=id
		if (!id)
			return ["error":"id missing"]
		if (!username)
			return ["error":"username missing"]
		if (!code)
			return ["error":"code missing"]
		usersRolesService.updateUser(example)
		["action":"success"]
	}

	@RequestMapping("/user/create.do")
	public @ResponseBody Object user_create(String code,String username,String password,String main_role_name,String department,String major){
		Role main_role=usersRolesService.findRoleByName(main_role_name)
		User example=new User(username:username,code:code,password:password,main_role:main_role,department:department,major:major)
		if (!username)
			return ["error":"username missing"]
		if (!code)
			return ["error":"code missing"]
		usersRolesService.createUser(example)
		["action":"success"]
	}
	

	@RequestMapping("/user/delete.do")
	public @ResponseBody Object user_delete(Long userId){
		usersRolesService.deleteUser(userId)		
		["action":"success"]
	}

	@RequestMapping("/users/all.json")
	public @ResponseBody Object users_all(){
		List users=usersRolesService.findUsers()

		[
			"action":"success",
			"recordsTotal":users.size(),
			"data":users
		]
	}
	@RequestMapping("/users/allStudents.json")
	public @ResponseBody Object users_allStudents(){
		List users=usersRolesService.findUsersByRoleName('student')
		[
			"action":"success",
			"recordsTotal":users.size(),
			"data":users
		]
	}
	@RequestMapping("/users/allTeachers.json")
	public @ResponseBody Object users_allTeachers(){
		List users=usersRolesService.findUsersByRoleName('teacher')

		[
			"action":"success",
			"recordsTotal":users.size(),
			"data":users
		]
	}
	@RequestMapping("/users/teachers/list1.json")
	public @ResponseBody Object users_teachers_list1(){
		def users=usersRolesService.findUsersByRoleName('teacher')
		users.collectAll {
			[
				id:it.id,
				code:it.code,
				name:it.username
			]
		}
	}
	@RequestMapping("/users/teachers/list2.json")
	public @ResponseBody Object users_teachers_list2(){
		def users=usersRolesService.findUsersByRoleName('teacher')
		users.collectAll {
			[
				id:it.id,
				text:it.code+' '+it.username
			]
		}
	}
	@RequestMapping("/roles/all.json")
	public @ResponseBody Object roles_all(){
		List roles=usersRolesService.findRoles()

		[
			"action":"success",
			"recordsTotal":roles.size(),
			"data":roles
		]
	}
}
