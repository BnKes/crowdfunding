package com.atguigu.atcrowdfunding.manager.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/certtype")
public class CerttypeController {
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map){
		
		return "certtype/index";
	}
}
