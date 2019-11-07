package com.atguigu.atcrowdfunding.manager.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	
	
	@Autowired
	UserService userService;
	
	//异步分页查询
	@RequestMapping("/toIndex")
	public String toIndex(){
		return "user/index";  //异步，先直接到user/index.jsp，数据待会异步传入
	}
	
	//异步分页查询
	@ResponseBody
	@RequestMapping("/index")
	public Object index(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
	@RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize){
		
		AjaxResult result = new AjaxResult();
		
		try {
			Page page = userService.queryPage(pageno, pagesize);
			
			result.setSuccess(true);
			result.setPage(page);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询数据失败！");
		}
		return result;  //以流的形式传入序列化JSON数据
	}
	
	
	
	
//	//同步分页查询
//	@RequestMapping("/index")
//	//防止空指针异常，给默认值
//	public String index(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
//			@RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize,Map map){
//		
//		Page page = userService.queryPage(pageno, pagesize); //pageno当前页数，pagesize每页的数量
//		
//		map.put("page", page);
//		
//		return "user/index";
//	}

}
