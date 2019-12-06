package com.atguigu.atcrowdfunding.potal.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.aspectj.apache.bcel.classfile.ConstantObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Ticket;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.CertService;
import com.atguigu.atcrowdfunding.manager.service.CerttypeService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.TicketService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CerttypeService certtypeService;
	
	@RequestMapping("/accttype")
	public String accttype(){
		
		return "member/accttype";
	}
	
	//判断流程已经进行到的步骤
	@RequestMapping("/apply")
	public String apply(HttpSession session){
		Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
		Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId());

		if(ticket==null){
			ticket = new Ticket();
			ticket.setMemberid(loginMember.getId());
			ticket.setPstep("apply");
			ticket.setStatus("0");
			ticketService.saveTicket(ticket);
		}else{
			if("accttype".equals(ticket.getPstep())){
				return "redirect:/member/basicinfo.htm";
			}else if("basicinfo".equals(ticket.getPstep())){
				return "redirect:/member/uploadCert.htm";
			}else if("uploadCert".equals(ticket.getPstep())){
				return "redirect:/member/checkemail.htm";
			}else if("checkemail".equals(ticket.getPstep())){
				return "redirect:/member/checkauthcode.htm";
			}
		}
		return "member/accttype";
	}

	
	@RequestMapping("/basicinfo")
	public String basicinfo(HttpSession session, Map map){
		return "member/basicinfo";
	}
	
	@RequestMapping("/uploadCert")
	public String uploadCert(HttpSession session){
		Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
		
		String accttype = loginMember.getAccttype();
		List<Cert> queryCertByAccttype = certtypeService.queryCertByAccttype(accttype);
		session.setAttribute("queryCertByAccttype", queryCertByAccttype);
		
		return "member/uploadCert";
	}
	
	@ResponseBody
	@RequestMapping("/updateBasicinfo")
	public Object updateBasicinfo(Member member, HttpSession session){  
		
		AjaxResult result = new AjaxResult();
		
		try {
			// 获取登录会员的id值用于做主键更新,同时更新session域中的值
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setRealname(member.getRealname());
			loginMember.setCardnum(member.getCardnum());
			loginMember.setTel(member.getTel());
			
			int count = memberService.updateBasicinfo(loginMember);
			
			//更新流程步骤:
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId()) ;
			ticket.setPstep("basicinfo");
			ticketService.updatePstep(ticket);
			
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
	public Object updateAcctType(String accttype,HttpSession session){  
		
		AjaxResult result = new AjaxResult();
		
		try {
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setAccttype(accttype);
			
			// 更新账户类型
			int count = memberService.updateAcctType(loginMember);
			
			//记录流程步骤:
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId()) ;
			ticket.setPstep("accttype");
			ticketService.updatePstep(ticket);
			
			result.setSuccess(1==count);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("添加数据失败！");
		}
		return result;  //以流的形式传入序列化JSON数据
	}
}
