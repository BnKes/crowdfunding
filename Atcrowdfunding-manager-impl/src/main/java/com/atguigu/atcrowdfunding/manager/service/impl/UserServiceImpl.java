package com.atguigu.atcrowdfunding.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.exception.LoginFailException;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

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
	public Page queryPage(Map<String,Object> paramMap) {
		
		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		Integer startIndex = page.getStartIndex();
		paramMap.put("startIndex", startIndex);
		
		List<User> data = userMapper.queryList(paramMap);
		
		page.setData(data);
		
		Integer totalsize = userMapper.queryCount(paramMap);
		page.setTotalsize(totalsize);
		
		return page;
	}

//	@Override
//	public Page queryPage(Integer pageno, Integer pagesize) {
//		
//		Page page = new Page(pageno,pagesize);
//		Integer startIndex = page.getStartIndex();
//		
//		List<User> datas = userMapper.queryList(startIndex, pagesize);
//		
//		page.setDatas(datas);
//		
//		Integer totalsize = userMapper.queryCount();
//		page.setTotalsize(totalsize);
//		
//		return page;
//	}

	@Override
	public int saveUser(User user) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String createtime = sdf.format(date);
		user.setCreatetime(createtime);
		user.setUserpswd(MD5Util.digest(Const.PASSWORD));
		
		return userMapper.insert(user);
	}


	@Override
	public User getUserById(Integer id) {
		
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}


	@Override
	public int updateUser(User user) {

		return userMapper.updateByPrimaryKey(user);
	}


	@Override
	public int deleteUser(Integer id) {
		
		return userMapper.deleteByPrimaryKey(id);
	}


	@Override
	public int deleteBatchUserByVO(Data data) {
		
		return userMapper.deleteBatchUserByVO(data.getDatas());
	}


	@Override
	public List<Role> queryAllRole() {
		
		return userMapper.queryAllRole();
	}


	@Override
	public List<Integer> queryRoleByUserid(Integer id) {
		
		return userMapper.queryRoleByUserid(id);
	}


	@Override
	public int saveUserRoleRelationship(Integer userid, Data data) {
		return userMapper.saveUserRoleRelationship(userid,data);
	}


	@Override
	public int deleteUserRoleRelationship(Integer userid, Data data) {
		
		return userMapper.deleteUserRoleRelationship(userid,data);
	}


	@Override
	public List<Permission> queryPermissionByUserid(Integer id) {

		return userMapper.queryPermissionByUserid(id);
	}
	
		
	}

