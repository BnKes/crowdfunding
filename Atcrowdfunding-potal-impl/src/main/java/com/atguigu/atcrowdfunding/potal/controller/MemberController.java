package com.atguigu.atcrowdfunding.potal.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.activiti.bpmn.model.Task;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.TaskQuery;
import org.aspectj.apache.bcel.classfile.ConstantObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.Ticket;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.CertService;
import com.atguigu.atcrowdfunding.manager.service.CerttypeService;
import com.atguigu.atcrowdfunding.potal.listener.PassListener;
import com.atguigu.atcrowdfunding.potal.listener.RefuseListener;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.TicketService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.vo.Data;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CerttypeService certtypeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired 
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TicketService tickerService;
	
	@RequestMapping("/accttype")
	public String accttype(){
		
		return "member/accttype";
	}
	
	@RequestMapping("/basicinfo")
	public String basicinfo(HttpSession session){
		return "member/basicinfo";
	}
	
	@RequestMapping("/checkemail")
	public String checkemail(){
		
		return "member/checkemail";
	}
	
	@RequestMapping("/checkauthcode")
	public String checkauthcode(){
		
		return "member/checkauthcode";
	}
	
	@RequestMapping("/uploadCert")
	public String uploadCert(HttpSession session){
		Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
		
		String accttype = loginMember.getAccttype();
		List<Cert> queryCertByAccttype = certtypeService.queryCertByAccttype(accttype);
		session.setAttribute("queryCertByAccttype", queryCertByAccttype);
		
		return "member/uploadCert";
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
	
	@ResponseBody
	@RequestMapping("/doUploadCert")
	public Object doUploadCert( HttpSession session, Data ds) {  //Data自动封装前端的表单数据
		AjaxResult result = new AjaxResult();
		
		System.out.println(11);
		try {
			// 获取登录会员信息
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);	
			String realPath = session.getServletContext().getRealPath("/pics");
			List<MemberCert> certimgs = ds.getCertimgs();
			for(MemberCert memberCert : certimgs){
				MultipartFile fileImg = memberCert.getFileImg();
				String originalFilename = fileImg.getOriginalFilename();
				String extName = originalFilename.substring(originalFilename.lastIndexOf(".")); //扩展名.img
				String tmpName = UUID.randomUUID().toString() + extName;
				String filename = realPath + "/cert" + "/" + tmpName;
				fileImg.transferTo(new File(filename));  //上传文件
				
				//在数据库中保存文件名和id
				memberCert.setIconpath(tmpName);
				memberCert.setMemberid(loginMember.getId());
			}
			
			//在保存数据库中保存文件名和id
			certtypeService.saveMemberCert(certimgs);
			//记录流程步骤
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId());
			ticket.setPstep("uploadCert");
			ticketService.updatePstep(ticket);
			
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/startProcess")
	public Object startProcess( HttpSession session, String email) {  //Data自动封装前端的表单数据
		AjaxResult result = new AjaxResult();
		
		try {
			// 获取登录会员信息
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			
			// 如果用户输入新的邮箱,将旧的邮箱地址替换
			if(!loginMember.getEmail().equals(email)){
				loginMember.setEmail(email);
				memberService.updateEmail(loginMember);
			}
		
			//启动实名认证流程 - 系统自动发送邮件,生成验证码.验证邮箱地址是否合法. 
			
			//1.根据流程id 查询出流程
			ProcessDefinition  processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("auth").singleResult();
			
			//随机生成4位验证码
			StringBuilder authcode = new StringBuilder();
			for (int i = 1; i <= 4; i++) {
				authcode.append(new Random().nextInt(10)); //0-9的随机整数
			}
			
			//2.准备流程变量值
			Map<String, Object> variables = new HashMap<String,Object>();
			variables.put("toEmail", email);
			variables.put("authcode",authcode );
			//分配当前完成“审核验证码”任务
			variables.put("loginacct", loginMember.getLoginacct());
			variables.put("passListener",new PassListener() );
			variables.put("refuseListener", new RefuseListener());
			
			//3.注入流程变量，启动流程,获得实例
			//发送验证码
			ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
			
			//4.在t_ticket记录流程步骤,piid,验证码，用于验证
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId());
			ticket.setPstep("checkemail");
			ticket.setPiid(processInstance.getProcessInstanceId());
			ticket.setAuthcode(authcode.toString());
			ticketService.updatePiidAndPstep(ticket);
			
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/finishApply")
	public Object finishApply( HttpSession session, String authcode) {  //Data自动封装前端的表单数据
		AjaxResult result = new AjaxResult();
		
		try {
			// 获取登录会员信息
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			
			//查询数据库中的验证码
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId());
			
			if(authcode.equals(ticket.getAuthcode())){
				//查询当前用户的任务（即被分配的“审核验证码”任务）
//				org.activiti.engine.task.Task task = taskService.createTaskQuery().processInstanceId(ticket.getPiid()).taskAssignee(loginMember.getLoginacct()).singleResult();
				TaskQuery processInstanceId = taskService.createTaskQuery().processInstanceId(ticket.getPiid());
				org.activiti.engine.task.Task task = processInstanceId.taskAssignee(loginMember.getLoginacct()).singleResult();
				//完成审核
				taskService.complete(task.getId());
				
				//更新用户申请状态
				loginMember.setAuthstatus("1");
				memberService.updateAuthstatus(loginMember);
				
				//记录流程步骤:			
				ticket.setPstep("finishapply");
				ticketService.updatePstep(ticket);
				
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
				result.setMessage("验证码不正确,请重新输入!");
			}
			}catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}
			return result;
		}
}
