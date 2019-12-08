package com.atguigu.atcrowdfunding.potal.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Member;

public interface MemberService {

	Member queryMemberLogin(Map<String, Object> paramMap);

	int updateAcctType(Member member);

	int updateBasicinfo(Member loginMember);

	void updateEmail(Member loginMember);

	void updateAuthstatus(Member loginMember);

	Member getMemberById(int memberid);

	List<Map<String, Object>> queryCertByMemberid(int memberid);


}
