package com.atguigu.atcrowdfunding.potal.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.apache.bcel.classfile.ConstantObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/apply")
	public String apply(){
		return "member/accttype";
	}
	
	@RequestMapping("/basicinfo")
	public String basicinfo(){
		return "member/basicinfo";
	}
	
	@ResponseBody
	@RequestMapping("/updateBasicinfo")
	public Object updateBasicinfo(Member member, HttpSession session){  //由前端传来的数据自动封装成User
		
		AjaxResult result = new AjaxResult();
		
		try {
			// 获取登录会员的id值用于做主键更新,同时更新session域中的值
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setRealname(member.getRealname());
			loginMember.setCardnum(member.getCardnum());
			loginMember.setTel(member.getTel());
			
			int count = memberService.updateBasicinfo(loginMember);//需要在impl中手动添加默认密码和createtime
			
			result.setSuccess(count==1);			
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("添加数据失败！");
		}
		return result;  //以流的形式传入序列化JSON数据
	}
	//增加
	@ResponseBody
	@RequestMapping("/updateAcctType")
	public Object doAdd(String accttype,HttpSession session){  //由前端传来的数据自动封装成User
		
		AjaxResult result = new AjaxResult();
		
		try {
			Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			member.setAccttype(accttype);
			int count = memberService.updateAcctType(member);//需要在impl中手动添加默认密码和createtime
			result.setSuccess(1==count);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("添加数据失败！");
		}
		return result;  //以流的形式传入序列化JSON数据
	}
}
