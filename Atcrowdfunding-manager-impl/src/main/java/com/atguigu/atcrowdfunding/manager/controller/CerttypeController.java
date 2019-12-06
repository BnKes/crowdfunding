package com.atguigu.atcrowdfunding.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.AccountTypeCert;
import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.CerttypeService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

@Controller
@RequestMapping("/certtype")
public class CerttypeController {
	
	@Autowired
	private CerttypeService certtypeService;
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		
		List<Cert> allCert = certtypeService.queryAllCert();
		map.put("allCert", allCert);
		
		//查询资质与账户类型之间关系(表示之前给账户类型分配过资质)
		List<Map<String,Object>> certAccttypeList =  certtypeService.queryCertAccttype();
		System.out.println(certAccttypeList.toString());
		map.put("certAccttypeList",certAccttypeList);
		
		return "certtype/index";
	}
	
	//增加
	@ResponseBody
	@RequestMapping("/insertAcctTypeCert")
	public Object insertAcctTypeCert(AccountTypeCert accountTypeCert){  //由前端传来的数据自动封装
		
		AjaxResult result = new AjaxResult();
		
		try {
			if(accountTypeCert.getCertid()==null || accountTypeCert.getAccttype()==null){
				result.setSuccess(false);
				result.setMessage("添加数据失败！");
			}else{				
				int count = certtypeService.insertAcctTypeCert(accountTypeCert);
				result.setSuccess(count==1);			
			}
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("添加数据失败！");
		}
		return result;  //以流的形式传入序列化JSON数据
	}
	
	//删除
	@ResponseBody
	@RequestMapping("/deleteAcctTypeCert")
	public Object deleteAcctTypeCert(AccountTypeCert accountTypeCert){  //由前端传来的数据自动封装
		
		AjaxResult result = new AjaxResult();
		
		try {
			int count = certtypeService.deleteAcctTypeCert(accountTypeCert);
			result.setSuccess(count==1);			
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("添加数据失败！");
		}
		return result;  //以流的形式传入序列化JSON数据
	}
	
	
}
