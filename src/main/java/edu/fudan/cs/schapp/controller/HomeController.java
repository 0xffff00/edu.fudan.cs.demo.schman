package edu.fudan.cs.schapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fudan.nisl.auth.RequireUserLogin;

 

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
	
}