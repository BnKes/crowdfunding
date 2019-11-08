package com.atguigu.atcrowdfunding.manager.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.Page;

public interface UserService {

	User queryUserLogin(Map<String, Object> paramMap);
	

//	Page queryPage(Integer pageno, Integer pagesize);
	
	int saveUser(User user);

	//模糊查询分页
	Page queryPage(Map<String,Object> paramMap);


	User getUserById(Integer id);


	int updateUser(User user);

}
