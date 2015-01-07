package edu.fudan.cs.schapp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User
import edu.fudan.cs.schapp.service.UsersRolesService
import edu.fudan.nisl.auth.RequirePermission

 

/**
 * 基于角色的访问控制(RBAC) 系统管理模块 AJAX数据交互API
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014年12月15日
 * @since groovy 2.3
 */
@RequirePermission("igc.mydata.sysman")
@Controller
@RequestMapping("/sys")
class SysManController {

	@Autowired
	UsersRolesService usersRolesService


	@RequestMapping("/user/update.do")
	public @ResponseBody Object user_update(Long id,String username,String password,Long main_role_id){
		Role main_role=usersRolesService.findRoleById(main_role_id)
		User example=new User(id:id,username:username,password:password,main_role:main_role)
		if (!id)
			return ["error":"id missing"]
		 
		usersRolesService.updateUser(example)
		["action":"success"]
	}

	@RequestMapping("/user/create.do")
	public @ResponseBody Object user_create(String username,String password,Long main_role_id){
		Role main_role=usersRolesService.findRoleById(main_role_id)
		User example=new User(username:username,password:password,main_role:main_role)
		if (!username)
			return ["error":"username missing"]
		usersRolesService.createUser(example)
		["action":"success"]
	}
	
	@Deprecated
	@RequestMapping("/user/create1.do")
	public @ResponseBody Object user_create1(String username,String password,String section,String phone,String email,String main_role_id){
		def example=[
			username:username
			,password:password
			,section:section
			,phone:phone
			,email:email
			,main_role_id:main_role_id
		]
		usersRolesService.createUser(example)
		["action":"success"]
	}

	@RequestMapping("/user/delete.do")
	public @ResponseBody Object user_delete(Long userId){
		try {
			usersRolesService.deleteUser(userId)
		} catch (Exception e) {
			return ["error":e.toString()]
		}
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
