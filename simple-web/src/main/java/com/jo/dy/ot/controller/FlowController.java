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

	@RequestMapping("/deploy")
	public Result upload(SysWorkflow sysWorkflow, String stepArr){
		sysWorkflow.setProcessKey("_"+UUID.randomUUID().toString());
//		return new Result();
		return sysWorkflowService.simpleSave(sysWorkflow, stepArr);
	}
	
}
