package com.jo.dy.ot.process.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

@Component("processService")
public class ProcessService {

	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource
	private TaskService taskService;
	
	public void startProcess(String processDefinitionKey,Map<String,Object> variables) {
		runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
	}
	
	public List<Task> listTask(String assignee){
		return taskService.createTaskQuery().taskAssignee(assignee).list();
		
	}
	
}
