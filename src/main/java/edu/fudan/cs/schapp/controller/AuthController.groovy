package edu.fudan.cs.schapp.controller;

import javax.servlet.http.HttpServletRequest

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import edu.fudan.nisl.auth.RequireUserLogin
import edu.fudan.cs.schapp.service.UsersRolesService

/**
 * 用户登录验证模块
 * 
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014年11月26日
 * @since groovy 2.3
 */

@Controller
@RequestMapping("/auth")
class AuthController {

	private static final Log log=LogFactory.getLog(AuthController.class)

	@Autowired
	UsersRolesService usersRolesService

	@RequestMapping("/current.json")
	public @ResponseBody Object current(HttpServletRequest request) {

		Map user=request.getSession()?.getAttribute("user")
		log.debug("auth/current.json: "+user)
		if (user){
			return user
		}else{
			return [:]	//empty map
		}
	}


	@RequestMapping("/user/exist.json")
	public @ResponseBody Integer existUsername(String username){
		Map one=usersRolesService.findUserByName(username)
		one?1:0
	}

	@RequestMapping("/login.do")
	public @ResponseBody Object login(String username, String password, HttpServletRequest request) {
		Map user = usersRolesService.findUserByNameWithMainRole(username)

		if (!user || password != user['PASSWORD']){
			return ["error":"用户名或密码不正确！"]
		}
		List permNames=usersRolesService.findPermNamesByUser(user)
		user['permissions']=permNames
		request.getSession().setAttribute("user", user)
		//request.getSession().setAttribute("userid", user['ID'])
		//request.getSession().setAttribute("username", user['USERNAME'])

		log.debug("auth/login.do: "+user)
		["action":"success","USERNAME":user['USERNAME']]
	}

	@RequestMapping("/logoff.do")
	public @ResponseBody Object logoff(HttpServletRequest request) {
		request.getSession().removeAttribute("user")
		//request.getSession().removeAttribute("userid")
		//request.getSession().removeAttribute("username")

		log.debug("auth/logoff.do: success")
		["action":"success"]
	}


	@RequireUserLogin
	@RequestMapping("/user/current/update.do")
	public @ResponseBody Object user_current_update(String password,String section,String phone,String email,HttpServletRequest request){
		Map user=request.getSession().getAttribute("user")
		long uid=user['ID']
		Map changes=[id:uid,password:password,section:section,phone:phone,email:email]
		changes=changes.findAll{it.value !=null}
		usersRolesService.updateUser(changes)
		["action":"success"]
	}

	@RequireUserLogin
	@RequestMapping("/user/current/brief.json")
	public @ResponseBody Object user_current_brief(HttpServletRequest request){
		Map user=request.getSession().getAttribute("user")
		if (!user) return [:]
		usersRolesService.findUserByIdWithMainRoleSafely(user['ID'].toString().toLong())
	}
}
