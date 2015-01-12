package edu.fudan.cs.schapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fudan.cs.auth.RequireUserLogin;

 

/**
 * 主要控制器
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version 1.8
 */
@RequireUserLogin
@Controller
@RequestMapping("")
public class HomeController {

 
	
	@RequestMapping("/")
	public String root() {		
		return "home";
	}
	@RequestMapping("/home")
	public String home() {		
		return "home";
	}
	@RequestMapping("/login")
	public String login() {		
		return "login";
	}
	@RequestMapping("/manage/students")
	public String manage_students() {		
		return "manage_students";
	}
	@RequestMapping("/manage/teachers")
	public String manage_teachers() {		
		return "manage_teachers";
	}
	@RequestMapping("/manage/courses")
	public String manage_courses() {		
		return "manage_courses";
	}
	@RequestMapping("/student/select_courses")
	public String student_select_courses() {		
		return "student_select_courses";
	}
}