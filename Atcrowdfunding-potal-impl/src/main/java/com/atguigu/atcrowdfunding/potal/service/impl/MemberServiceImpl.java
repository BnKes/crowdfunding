package com.atguigu.atcrowdfunding.potal.service.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public int updateAcctType(Member member) {
		int count = memberMapper.updateAcctType(member);
		return count;
	}

	@Override
	public int updateBasicinfo(Member loginMember) {
		int count = memberMapper.updateBasicinfo(loginMember);
		return count;
	}

	@Override
	public void updateEmail(Member loginMember) {
		memberMapper.updateEmail(loginMember);
		
	}

	@Override
	public void updateAuthstatus(Member loginMember) {
		memberMapper.updateAuthstatus(loginMember);
		
	}

	@Override
	public Member getMemberById(int memberid) {
		
		return memberMapper.getMemberById(memberid);
	}

	@Override
	public List<Map<String, Object>> queryCertByMemberid(int memberid) {

		return memberMapper.queryCertByMemberid(memberid);
	}

	@Override
	public void deleteMemberCertbyMemberId(Integer memberid) {
		memberMapper.deleteMemberCertbyMemberId(memberid);
		
	}

}
