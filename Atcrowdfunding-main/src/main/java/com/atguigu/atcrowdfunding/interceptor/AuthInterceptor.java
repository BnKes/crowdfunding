package com.atguigu.atcrowdfunding.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.atcrowdfunding.util.Const;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 
		 Set<String> allURIs = (Set<String>) request.getSession().getServletContext().getAttribute(Const.ALL_PERMISSION_URI);
		 String servletPath = request.getServletPath();
		 
		 if(allURIs.contains(servletPath)){
			 
			 Set<String> myURIs = (Set<String>) request.getSession().getAttribute(Const.MY_URIS);
			 if(myURIs.contains(servletPath)){
				 return true;
			 }else{
				 response.sendRedirect(request.getContextPath()+"/login.htm");//权限不足，返回登录界面
				 return false;
			 }
		 }else{
			 return true; //不属于分辨是否要拦截的路径，任何人都可以访问
		 }
	}
}
