package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.vo.Data;



@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	PermissionService permissionService ;
	
	
	@ResponseBody
	@RequestMapping("/deletePermission")
	public Object deletePermission(Integer id){
		AjaxResult result = new AjaxResult();
		try {
			int count = permissionService.deletePermission(id);
			
			result.setSuccess(count==1);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除许可树数据失败!");
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/doUpdate")
	public Object doUpdate(Permission permission){
		AjaxResult result = new AjaxResult();
		try {
			int count = permissionService.updatePermission(permission);
			
			result.setSuccess(count==1);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(Integer id,Map map){
		Permission permission = permissionService.getPermissionById(id);
		map.put("permission", permission);
		
		return "permission/update";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "permission/Add";
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Permission permission){
		AjaxResult result = new AjaxResult();
		try {
			int count = permissionService.savePermission(permission);
			
			result.setSuccess(count==1);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	
	@RequestMapping("/index")
	public String index(){
		return "permission/index";
	}
	
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData(){
		AjaxResult result = new AjaxResult();
		try {
			List<Permission> root = new ArrayList<Permission>();
			List<Permission> childrenPermissions = permissionService.queryAllPermission();
			
			Map<Integer,Permission> map = new HashMap<Integer,Permission>();
			
			for(Permission innerpermission: childrenPermissions){
				map.put(innerpermission.getId(),innerpermission );//把查出来的数据全部放入map中使用，不
			}
			
			for(Permission permission : childrenPermissions){  //利用递归，一个嵌套的Jason数据，通过F12-network看请求发送的数据
				Permission child = permission;
				
				if(child.getPid()==null){
					root.add(child);
				}else{
					Permission parent = map.get(child.getPid());
					parent.getChildren().add(child);
				}
			}
			result.setSuccess(true);		
			result.setData(root);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("加载许可数失败！");
		}

		return result ;
	}
/*	 * {"success":true,"message":null,"page":null,
	 * "data":[{"id":null,"pid":null,"name":"系统权限菜单","icon":null,"url":null,"open":true,
	 * "children":[{"id":null,"pid":null,"name":"控制面板","icon":null,"url":null,"open":false,"children":null},
	 * {"id":null,"pid":null,"name":"权限管理","icon":null,"url":null,"open":false,"children":null}]}]}*/

}
