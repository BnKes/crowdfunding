package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

/**
 * 
 * <p>Title: CertService.java<／p>
 * <p>Description: 资质模块的业务接口<／p>
 * <p>Copyright: Copyright (c) 2017<／p>
 * <p>Company: Atguigu<／p>
 * @author admin
 * @date 2017年8月1日
 * @version 1.0
 */
public interface CerttypeService {
	
	public List<Cert> queryAllCert();

}
