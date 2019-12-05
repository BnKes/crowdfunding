package com.atguigu.atcrowdfunding.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.manager.service.CertService;

@Controller
@RequestMapping("/cert")
public class CertController {
	
	@Autowired
	private CertService certService;
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		
		List<Cert> allCert = certService.queryAllCert();
		map.put("allCert", allCert);
		
		return "cert/index";
	}
}
