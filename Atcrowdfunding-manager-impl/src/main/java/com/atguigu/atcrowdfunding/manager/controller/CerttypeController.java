package com.atguigu.atcrowdfunding.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.manager.service.CerttypeService;

@Controller
@RequestMapping("/certtype")
public class CerttypeController {
	
	@Autowired
	private CerttypeService certtypeService;
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		
		List<Cert> allCert = certtypeService.queryAllCert();
		map.put("allCert", allCert);
		
		return "certtype/index";
	}
}
