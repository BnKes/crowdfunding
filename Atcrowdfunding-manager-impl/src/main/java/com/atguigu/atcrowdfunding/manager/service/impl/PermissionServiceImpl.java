package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public List<Permission> queryAllPermission() {
		
		return permissionMapper.queryAllPermission();
	}

	@Override
	public int savePermission(Permission permission) {
		return permissionMapper.insert(permission);
	}

	@Override
	public int updatePermission(Permission permission) {
		return permissionMapper.updateByPrimaryKey(permission);
	}

	@Override
	public Permission getPermissionById(Integer id) {
		return permissionMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public int deletePermission(Integer id) {
		return permissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
		return permissionMapper.queryPermissionidsByRoleid(roleid);
	}
	
	
}
