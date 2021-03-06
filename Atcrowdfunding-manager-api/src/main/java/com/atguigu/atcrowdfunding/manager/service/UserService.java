package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

public interface UserService {

	User queryUserLogin(Map<String, Object> paramMap);
	

//	Page queryPage(Integer pageno, Integer pagesize);
	
	int saveUser(User user);

	//模糊查询分页
	Page queryPage(Map<String,Object> paramMap);


	User getUserById(Integer id);


	int updateUser(User user);


	int deleteUser(Integer id);


	int deleteBatchUserByVO(Data data);


	List<Role> queryAllRole();


	List<Integer> queryRoleByUserid(Integer id);


	int saveUserRoleRelationship(Integer userid, Data data);


	int deleteUserRoleRelationship(Integer userid, Data data);


	List<Permission> queryPermissionByUserid(Integer id);


	

}
