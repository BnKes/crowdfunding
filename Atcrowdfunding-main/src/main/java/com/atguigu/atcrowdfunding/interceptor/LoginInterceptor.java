package com.atguigu.atcrowdfunding.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.Const;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Set<String> uri = new HashSet<String>();  //白名单，不拦截
		uri.add("/index.htm");   
		uri.add("/user/reg.do");   
		uri.add("/user/reg.htm");
		uri.add("/login.htm");
		uri.add("/doLogin.do");
		uri.add("/loginout.do");
		
		//获取请求路径
		String servletPath = request.getServletPath();
		if(uri.contains(servletPath)){
			return true; //放行，进入postHandle
		}
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Const.LOGIN_USER);
		if(user!=null){     //已登录
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/login.htm");
			return false;
		}
	}
}
