package com.atguigu.atcrowdfunding.potal.service.impl;

import java.util.Map;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.exception.LoginFailException;
import com.atguigu.atcrowdfunding.potal.dao.MemberMapper;
import com.atguigu.atcrowdfunding.potal.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public Member queryMemberLogin(Map<String, Object> paramMap) {
		
		Member member = memberMapper.queryMemberLogin(paramMap);
		
		if(member==null){
			throw new LoginFailException("登录失败，用户名或密码错误");
		}
		
		return member;
	}

}
