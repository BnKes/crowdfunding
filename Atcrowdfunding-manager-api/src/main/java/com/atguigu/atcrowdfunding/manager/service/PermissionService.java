package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.Permission;

public interface PermissionService {

	List<Permission> queryAllPermission();

	int savePermission(Permission permission);

	int updatePermission(Permission permission);

	Permission getPermissionById(Integer id);

	int deletePermission(Integer id);

	List<Integer> queryPermissionidsByRoleid(Integer roleid);
	
	
	
}
