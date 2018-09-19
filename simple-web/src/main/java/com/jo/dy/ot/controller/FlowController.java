package com.jo.dy.ot.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jo.dy.ot.entity.SysWorkflow;
import com.jo.dy.ot.service.SysWorkflowService;
import com.jo.dy.ot.util.Result;

@RestController
@RequestMapping("/flow")
public class FlowController {

	private Logger logger=Logger.getLogger(FlowController.class);
	
	@Resource
	private SysWorkflowService sysWorkflowService;

	/**
	 * 部署流程
	 */
	@RequestMapping("/deploy")
	public Result deploy(SysWorkflow sysWorkflow, String stepArr){
		sysWorkflow.setProcessKey("_"+UUID.randomUUID().toString());
		return sysWorkflowService.simpleSave(sysWorkflow, stepArr);
	}
	
	/**
	 * 获取所有的SysWorkflow对象
	 */
	@RequestMapping("/listProcess")
	public Result listProcess(){
		String customId="";
		return sysWorkflowService.listProcess(customId);
	}
	
	@RequestMapping("/listForm")
	public Result listForm(){
		String customId="";
		return sysWorkflowService.listBusiness(customId);
	}
	
	@RequestMapping("/bingdingFlow")
	public Result bingdingFlow(Long workflowId,Integer formId){
		return sysWorkflowService.bingdingFlow(workflowId,formId);
	}
	
}
