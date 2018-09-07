package com.jo.dy.ot.process.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("processService")
public class ProcessService {

	@Resource
	private RepositoryService repositoryService;

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private TaskService taskService;

	public ProcessInstance startProcess(String processDefinitionKey, String businessKey,
			Map<String, Object> variables) {
		return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
	}

	public List<Task> listTask(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();

	}

	// public void complateTask(String taskId) {
	// taskService.complete(taskId);
	// }

	@Transactional(rollbackFor = Exception.class)
	public void complateTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId())
				.singleResult();
		String businessKey = pi.getBusinessKey();
		taskService.complete(taskId);
		ProcessInstance pi2 = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId())
				.singleResult();
		if(pi2==null) {//任务结束
			String[] split = businessKey.split("\\.");
			
		}

	}

}
