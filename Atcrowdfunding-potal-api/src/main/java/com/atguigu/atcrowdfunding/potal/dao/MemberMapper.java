package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.Member;
import java.util.List;
import java.util.Map;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    Member selectByPrimaryKey(Integer id);

    List<Member> selectAll();

    int updateByPrimaryKey(Member record);

	Member queryMemberLogin(Map<String, Object> paramMap);

	int updateAcctType(Member member);

	int updateBasicinfo(Member member);

	void updateEmail(Member loginMember);

	void updateAuthstatus(Member loginMember);

	Member getMemberById(int memberid);

	List<Map<String, Object>> queryCertByMemberid(int memberid);

	void deleteMemberCertbyMemberId(Integer memberid);

}