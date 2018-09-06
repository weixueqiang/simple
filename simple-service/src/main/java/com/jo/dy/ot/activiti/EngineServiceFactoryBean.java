package com.jo.dy.ot.activiti;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.ProcessEngineImpl;

public class EngineServiceFactoryBean {

	private static EngineServiceFactoryBean EngineServiceFactoryBean=new EngineServiceFactoryBean();
	
	private EngineServiceFactoryBean() {
		
	}
	
	private ProcessEngineImpl processEngine;

	public ProcessEngineImpl getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(ProcessEngineImpl processEngine) {
		this.processEngine = processEngine;
	}
	
	public static TaskService getTaskService() {
		return EngineServiceFactoryBean.getProcessEngine().getTaskService();
	}
	
}
