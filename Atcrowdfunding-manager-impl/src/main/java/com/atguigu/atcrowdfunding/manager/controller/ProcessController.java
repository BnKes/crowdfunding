package com.atguigu.atcrowdfunding.manager.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;

@Controller
@RequestMapping("/process")
public class ProcessController {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping("/index")
	public String index(){
		return "process/index";
	}
	
	@RequestMapping("/showimg")
	public String showimg(){
		return "process/showimg";
	}
	
	@ResponseBody
	@RequestMapping("/showimgProDef")
	public void showimgProDef(String id, ServletResponse response){  
		
		try {
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
			InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
			ServletOutputStream outputStream = response.getOutputStream();
			IOUtils.copy(inputStream, outputStream);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(String id){  
		
		AjaxResult result = new AjaxResult();
		
		try {
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
			repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
			
			result.setSuccess(true);			
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除流程失败！");
		}
		return result; 
	}
	    
	
	@ResponseBody
	@RequestMapping("/deploy")
	public Object deploy(HttpServletRequest request) {
		AjaxResult result = new AjaxResult();
		System.out.println(33);
		
		try {
			MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)request;//文件需要将request转换为MultipartHttpServletRequest
			
			MultipartFile mfile = mreq.getFile("processDefFile");
			String originalFilename = mfile.getOriginalFilename();
			System.out.println(originalFilename);
			String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
			System.out.println(suffix);
			if(!suffix.equals("bpmn")){
				result.setMessage("不是bpmn文件，请重新上传");
				result.setSuccess(false);
			}else{
				repositoryService.createDeployment().addInputStream(mfile.getOriginalFilename(), mfile.getInputStream()).deploy();
			    result.setMessage("部署成功");
				result.setSuccess(true);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setMessage("部署失败");
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize ){
		AjaxResult result = new AjaxResult();
		
		Page page = new Page(pageno,pagesize);
		try {
			List<ProcessDefinition> totallist = repositoryService.createProcessDefinitionQuery().list();
			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().listPage(page.getStartIndex(), pagesize);
/*			出现自关联，无线循环，无法序列化为json串
 * 			page.setData(list);
			page.setTotalsize(list.size());
			result.setData(page);
			result.setMessage("加载数据成功");
			result.setSuccess(true);*/
			
			List<Map<String,Object>> mylistPage = new ArrayList<Map<String,Object>>();
			
			for(ProcessDefinition professionDefinition : list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id",professionDefinition.getId());
				map.put("name", professionDefinition.getName());
				map.put("key", professionDefinition.getKey());
				map.put("version", professionDefinition.getVersion());
				mylistPage.add(map);
			}
			
			page.setTotalsize(totallist.size());
			System.out.println(page.getTotalsize());
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
}
