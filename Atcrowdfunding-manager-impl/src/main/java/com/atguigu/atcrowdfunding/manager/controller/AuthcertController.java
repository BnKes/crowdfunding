package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.TicketService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;

@Controller
@RequestMapping("/authcert")
public class AuthcertController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private MemberService memberService; 
	
	@RequestMapping("/index")
	public String index(){
		return "authcert/index";
	}
	
	@RequestMapping("/show")
	public String show(int memberid, Map<String,Object> map){
		
		Member member = memberService.getMemberById(memberid);
		List<Map<String,Object>> certimgs = memberService.queryCertByMemberid(memberid);
		map.put("member", member);
		map.put("certimgs", certimgs);
		return "authcert/show";
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize ){
		AjaxResult result = new AjaxResult();
		
		Page page = new Page(pageno,pagesize);
		try {
			
			//查询auth流程的属于backuser组的任务
			TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey("auth").taskCandidateGroup("backuser");
			List<Task> list = taskQuery.listPage(page.getStartIndex(), pagesize);
			
			List<Map<String,Object>> mylistPage = new ArrayList<Map<String,Object>>();
			
			for(Task task : list){
				Map<String,Object> map = new HashMap<String,Object>();
				
				map.put("taskid", task.getId());
				map.put("taskName", task.getName());
				
				//根据task查询出流程
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
						.processDefinitionId(task.getProcessDefinitionId())
						.singleResult();
				map.put("procDefName",processDefinition.getName());
				map.put("procDefVersion", processDefinition.getVersion());
				
				//查询出申请人信息
				Member member = ticketService.getMemberByPiid(task.getProcessInstanceId());
				map.put("member", member);
				
				mylistPage.add(map);
			}
			
			Long count = taskQuery.count();
			page.setTotalsize(count.intValue());
			page.setData(mylistPage);
			
			result.setPage(page);
			result.setSuccess(true);
			result.setMessage("加载数据成功");
		} catch (Exception e) {
			result.setMessage("加载数据失败");
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pass")
	public Object pass( String taskid, Integer memberid ) {
		
		AjaxResult result = new AjaxResult();
		
		try {
			taskService.setVariable(taskid, "flag", true);
			taskService.setVariable(taskid, "memberid", memberid);
			
			taskService.complete(taskid);
			
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/refuse")
	public Object refuse( String taskid, Integer memberid ) {
		
		AjaxResult result = new AjaxResult();
		
		try {
			taskService.setVariable(taskid, "flag", false);
			taskService.setVariable(taskid, "memberid", memberid);
			
			taskService.complete(taskid);
			
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		
		return result;
	}
	
}
