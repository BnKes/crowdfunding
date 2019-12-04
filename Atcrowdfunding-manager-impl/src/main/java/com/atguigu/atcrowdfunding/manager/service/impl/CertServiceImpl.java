package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.manager.dao.CertMapper;
import com.atguigu.atcrowdfunding.manager.service.CertService;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

public class CertServiceImpl implements CertService {
	
	@Autowired
	private CertMapper certMapper;
	
	@Override
	public Cert queryCert(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Cert> pageQuery(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertCert(Cert cert) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cert queryById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCert(Cert cert) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCert(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCerts(Data ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cert> queryCertByAccttype(String accttype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cert> queryAllCert() {
		
		return certMapper.queryAllCert();
	}

	@Override
	public List<Map<String, Object>> queryAllAccttypeCert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertAccttypeCert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAccttypeCert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
