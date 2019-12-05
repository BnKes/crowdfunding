package com.atguigu.atcrowdfunding.potal.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Member;

public interface MemberService {

	Member queryMemberLogin(Map<String, Object> paramMap);

	int updateAcctType(Member member);

	int updateBasicinfo(Member loginMember);

}
