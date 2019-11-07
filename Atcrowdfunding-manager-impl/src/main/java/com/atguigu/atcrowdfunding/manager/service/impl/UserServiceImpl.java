package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.exception.LoginFailException;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.Page;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User queryUserLogin(Map<String, Object> paramMap) {
		User user = userMapper.queryUserlogin(paramMap);
				
		if(user==null){
			throw new LoginFailException("用户名或账号密码不正确");
		}
		return user;
	}

	@Override
	public Page queryPage(Integer pageno, Integer pagesize) {
		
		Page page = new Page(pageno,pagesize);
		Integer startIndex = page.getStartIndex();
		
		List<User> datas = userMapper.queryList(startIndex, pagesize);
		
		page.setDatas(datas);
		
		Integer totalsize = userMapper.queryCount();
		page.setTotalsize(totalsize);
		
		return page;
	}

	@Override
	public int saveUser(User user) {

		return userMapper.insert(user);
	}
	
		
	}

