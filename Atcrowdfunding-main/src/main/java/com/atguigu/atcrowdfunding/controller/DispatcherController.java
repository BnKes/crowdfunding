package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;

@Controller
public class DispatcherController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/member")
	public String member(){
		return "member/member";
	}
	
	@RequestMapping("/index")
	public String index(){		
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpSession session){		
		
		String logintype = null;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie : cookies){
				if("logincode".equals(cookie.getName())){
					String logincode = cookie.getValue();//loginacct=zhangsan&userpswd=202cb962ac59075b964b07152d234b70&logintype=member
					String[] split = logincode.split("&");
					if(split.length==3){
						String loginacct = split[0].split("=")[1];
						String userpswd = split[1].split("=")[1];
						logintype  = split[2].split("=")[1]; 
						Map<String, Object> paramMap = new HashMap<String,Object>();
						paramMap.put("loginacct",loginacct );
						paramMap.put("userpswd",userpswd );
						
						if("user".equals(logintype)){
							User user = userService.queryUserLogin(paramMap);
							session.setAttribute(Const.LOGIN_USER, user);
							//加载当前用户所拥有的许可权限
							List<Permission> myPermissions = userService.queryPermissionByUserid(user.getId());
							Permission permissionRoot = null;
							
							Set<String> myUris = new HashSet<String>();//存放登录者拥有的uri
							Map<Integer,Permission> map = new HashMap<Integer,Permission>();
							
							for(Permission innerpermission: myPermissions){
								map.put(innerpermission.getId(),innerpermission );//把查出来的数据全部放入map中使用，不
								myUris.add("/"+innerpermission.getUrl());	//数据库里的uri无"/"
							}
							
							for(Permission permission : myPermissions){  //利用递归，一个嵌套的Jason数据，通过F12-network看请求发送的数据
								Permission child = permission;
								
								if(child.getPid()==null){
									permissionRoot=permission;
								}else{
									Permission parent = map.get(child.getPid());
									parent.getChildren().add(child);
								}
							}
							session.setAttribute("permissionRoot", permissionRoot);
							session.setAttribute(Const.MY_URIS,myUris );
							
							return "redirect:/main.htm";
						}else if("member".equals(logintype)){
							Member member = memberService.queryMemberLogin(paramMap);
							session.setAttribute(Const.LOGIN_MEMBER, member);
							
							return "redirect:/member.htm";
						} 						
					}					
				}
			}
		}
			return "login";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){	
		
		session.invalidate();//销毁session对象
		return "redirect:/index.htm";
	}
	
	//异步请求
	@ResponseBody
	@RequestMapping("/doLogin")
	public Object doLogin(String loginacct,String userpswd,String type,String rememberme,HttpSession session,HttpServletResponse response){
		
		AjaxResult result = new AjaxResult();
		
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("loginacct", loginacct);
			paramMap.put("userpswd", MD5Util.digest(userpswd));
			paramMap.put("type", type);
			
			if(type.equals("member")){
				Member member = memberService.queryMemberLogin(paramMap);
				session.setAttribute(Const.LOGIN_MEMBER, member);
				if("1".equals(rememberme)){
//					String logincode = "\"loginacct="+member.getLoginacct()+"&userpswd="+member.getUserpswd()+"&logintype=member\"";
					String logincode = "loginacct="+member.getLoginacct()+"&userpswd="+member.getUserpswd()+"&logintype=member";
					Cookie c = new Cookie("logincode", logincode);
					c.setMaxAge(60*60*24*14);
					c.setPath("/");//任何路径都能访问到cookie
					
					response.addCookie(c);
				}
				result.setData(type);
				result.setSuccess(true);
			}else if(type.equals("user")){
				User user = userService.queryUserLogin(paramMap);
				
				session.setAttribute(Const.LOGIN_USER, user);
				
				if("1".equals(rememberme)){
					
					String logincode = "loginacct="+user.getLoginacct()+"&userpswd="+user.getUserpswd()+"&logintype=user";
					System.out.println(logincode);
					Cookie c = new Cookie("logincode", logincode);
					c.setMaxAge(60*60*24*14);
					c.setPath("/");//任何路径都能访问到cookie
					
					response.addCookie(c);
				}
				
				//加载当前用户所拥有的许可权限
				List<Permission> myPermissions = userService.queryPermissionByUserid(user.getId());
				Permission permissionRoot = null;
				
				Set<String> myUris = new HashSet<String>();//存放登录者拥有的uri
				Map<Integer,Permission> map = new HashMap<Integer,Permission>();
				
				for(Permission innerpermission: myPermissions){
					map.put(innerpermission.getId(),innerpermission );//把查出来的数据全部放入map中使用，不
					myUris.add("/"+innerpermission.getUrl());	//数据库里的uri无"/"
				}
				
				for(Permission permission : myPermissions){  //利用递归，一个嵌套的Jason数据，通过F12-network看请求发送的数据
					Permission child = permission;
					
					if(child.getPid()==null){
						permissionRoot=permission;
					}else{
						Permission parent = map.get(child.getPid());
						parent.getChildren().add(child);
					}
				}
				
				session.setAttribute("permissionRoot", permissionRoot);
				session.setAttribute(Const.MY_URIS,myUris );
				result.setData(type);
				result.setSuccess(true);
			}else{
				result.setMessage("登录出错");
				result.setSuccess(false);
			}
			
		
		} catch (Exception e) {
			result.setMessage("登录失败，用户名或密码错误");
			e.printStackTrace();
			
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	//同步请求
//	@RequestMapping("/doLogin")
//	public String doLogin(String loginacct,String userpswd,String type,HttpSession session){
//		
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("loginacct", loginacct);
//		paramMap.put("userpswd", userpswd);
//		paramMap.put("type", type);
//	
//		User user = userService.queryUserLogin(paramMap);
//		
//		session.setAttribute(Const.LOGIN_USER, user);
//		
//		return "redirect:/main.htm";
//	}
	
}
