package com.atguigu.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(Integer id){  //由前端传来的数据自动封装成User
		
		AjaxResult result = new AjaxResult();
		
		try {
			int count = userService.deleteUser(id);
			
			result.setSuccess(count==1);			
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除数据失败！");
		}
		return result; 
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(Integer id,Map map){
		
		User user = userService.getUserById(id);
		map.put("user", user);
		
		return "user/update";
	}
	
	@ResponseBody
	@RequestMapping("/doUpdate")
	public Object doUpdate(User user){  //由前端传来的数据自动封装成User
		
		AjaxResult result = new AjaxResult();
		
		try {
			int count = userService.updateUser(user);
			
			result.setSuccess(count==1);			
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("修改数据失败！");
		}
		return result; 
	}
	
	
	@RequestMapping("/add")
	public String add(){
		return "user/add";
		}
	
	//增加
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(User user){  //由前端传来的数据自动封装成User
		
		AjaxResult result = new AjaxResult();
		
		try {
			int count = userService.saveUser(user);//需要在impl中手动添加默认密码和createtime
			
			result.setSuccess(count==1);			
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("添加数据失败！");
		}
		return result;  //以流的形式传入序列化JSON数据
	}
	
	//异步分页查询
	@RequestMapping("/toIndex")
	public String toIndex(){
		return "user/index";  //异步，先直接到user/index.jsp，数据待会异步传入
	}
	
	
	
	//异步分页模糊查询
	@ResponseBody
	@RequestMapping("/index")
	public Object index(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
	@RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize, String queryText){
		
		AjaxResult result = new AjaxResult();
		
		try {
			
			Map paramMap = new HashMap();
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);
			
			if(StringUtil.isNotEmpty(queryText)){
				if(queryText.contains("%")){
					//sql語句中默认%为匹配任何字符，需要转义，还需要其他转义，所以一共需要4个\
					queryText = queryText.replaceAll("%", "\\\\%");  
				}
				paramMap.put("queryText", queryText);  // \%
			}
			
			Page page = userService.queryPage(paramMap);
			
			result.setSuccess(true);
			result.setPage(page);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询数据失败！");
		}
		return result;  //以流的形式传入序列化JSON数据
	}
	
//	//异步分页查询
//	@ResponseBody
//	@RequestMapping("/index")
//	public Object index(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
//	@RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize){
//		
//		AjaxResult result = new AjaxResult();
//		
//		try {
//			Page page = userService.queryPage(pageno, pagesize);
//			
//			result.setSuccess(true);
//			result.setPage(page);
//			
//		} catch (Exception e) {
//			result.setSuccess(false);
//			e.printStackTrace();
//			result.setMessage("查询数据失败！");
//		}
//		return result;  //以流的形式传入序列化JSON数据
//	}
	
	
	
	
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
