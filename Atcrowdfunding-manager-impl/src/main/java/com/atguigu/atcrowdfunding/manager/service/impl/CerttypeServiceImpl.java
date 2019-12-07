package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.AccountTypeCert;
import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.manager.dao.CerttypeMapper;
import com.atguigu.atcrowdfunding.manager.service.CerttypeService;
import com.atguigu.atcrowdfunding.util.Page;

@Service
public class CerttypeServiceImpl implements CerttypeService {
	
	@Autowired
	private CerttypeMapper certtypeMapper;

	@Override
	public List<Cert> queryAllCert() {
		
		return certtypeMapper.queryAllCert();
	}

	@Override
	public List<Map<String, Object>> queryCertAccttype() {
		List<Map<String, Object>> queryCertAccttype = certtypeMapper.queryCertAccttype();
		return queryCertAccttype;
	}

	@Override
	public int insertAcctTypeCert(AccountTypeCert accountTypeCert) {
	
		return certtypeMapper.insertAcctTypeCert(accountTypeCert);
	}

	@Override
	public int deleteAcctTypeCert(AccountTypeCert accountTypeCert) {
		
		return certtypeMapper.deleteAcctTypeCert(accountTypeCert);
	}

	@Override
	public List<Cert> queryCertByAccttype(String accttype) {
		
		return certtypeMapper.queryCertByAccttype(accttype);
	}

	@Override
	public void saveMemberCert(List<MemberCert> certimgs) {
		
		certtypeMapper.saveMemberCert(certimgs);
	}



}
