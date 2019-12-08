package com.atguigu.atcrowdfunding.potal.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.TicketService;
import com.atguigu.atcrowdfunding.util.ApplicationContextUtils;

public class RefuseListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {

		Integer memberid = (Integer)execution.getVariable("memberid");
		
		//ApplicationContext ioc =  new ClassPathXmlApplicationContext(""); //不能自己创建ioc容器,保证容器是唯一的.
		//ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(application); //需要获取application对象,可以使用流程变量,或ThreadLocal	
		
		
		//获取IOC容器.通过自定义的工具类,实现Spring接口,以接口注入的方式获取IOC容器对象.
		ApplicationContext applicationContext = ApplicationContextUtils.applicationContext;
		
		//获取TicketService, MemberService对象.
		TicketService ticketService = applicationContext.getBean(TicketService.class);
		MemberService memberService = applicationContext.getBean(MemberService.class);
		
		//更新t_member表的authstatus字段: 1 ->0 -  未实名认证
		Member member = memberService.getMemberById(memberid);
		member.setAuthstatus("0");
		memberService.updateAuthstatus(member);
		
		//更新t_ticket表的status字段 0 -> 1 表示流程结束
		ticketService.updateStatus(member);
	}

}
