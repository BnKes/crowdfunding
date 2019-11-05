package com.atguigu.atcrowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.manager.service.UserService;

@Controller
public class DispatcherController {

	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(){		
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){		
		return "login";
	}
	
}
