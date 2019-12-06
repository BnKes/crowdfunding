package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.AccountTypeCert;
import com.atguigu.atcrowdfunding.bean.Cert;
import java.util.List;
import java.util.Map;

public interface CerttypeMapper {

	List<Cert> queryAllCert();

	List<Map<String, Object>> queryCertAccttype();

	int insertAcctTypeCert(AccountTypeCert accountTypeCert);

	int deleteAcctTypeCert(AccountTypeCert accountTypeCert);

	List<Cert> queryCertByAccttype(String accttype);

}