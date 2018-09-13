package com.jo.dy.ot.process.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.jo.dy.ot.dao.SysFlowFormMapper;
import com.jo.dy.ot.dao.SysWorkflowMapper;
import com.jo.dy.ot.entity.SysWorkflowExample;
import com.jo.dy.ot.service.ProcessService;
import com.jo.dy.ot.util.Result;

import simple.activiti.test.WorkFlowServiceTest;


@Component("processService")
public class ProcessServiceImpl implements ProcessService{

	@Resource
	private RepositoryService repositoryService;

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private TaskService taskService;
	
	@Resource
	private HistoryService historyService;
	@Resource
	private SysWorkflowMapper sysWorkflowMapper;
	@Resource
	private SysFlowFormMapper sysFlowFormMapper;

	public ProcessInstance startProcess(String processDefinitionKey, String businessKey,
			Map<String, Object> variables) {
		return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
	}

	public List<Task> listTask(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();

	}

	

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

	@Override
	public List<Map<String,Object>> listByAssignee(String id, String processDefinitionKey) {
		if(StringUtils.isAnyBlank(id,processDefinitionKey)) {
			return null;
		}
		List<Task> list = taskService.createTaskQuery().taskAssignee(id)
				.processDefinitionKey(processDefinitionKey)
				.list();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<Map<String,Object>> params=new ArrayList<>();
		for(Task task:list) {
			Map<String,Object> param=new HashMap<>();
			param.put("id",task.getId());
			param.put("name", task.getName());
			params.add(param);
		}
		return params;
		
	}

	@Override
	public List<String> listFlow(String processInstanceId, String processDefinitionId) {
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);

		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		// 获取当前活动ID
		String activityId = pi.getActivityId();
		// 4：获取当前的活动
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);

		// 5:获取当前活动完成之后连线的名称
		List<PvmTransition> pvmList = activityImpl.getOutgoingTransitions();
		List<String> list=new ArrayList<String>();
		if(CollectionUtils.isEmpty(pvmList)) {
			list.add("默认提交");
			return list;
		}
		for (PvmTransition pvm : pvmList) {
			String name = (String) pvm.getProperty("name");
			if (StringUtils.isNotBlank(name)) {
				list.add(name);
			} else {
				list.add("默认提交");
			}
		}
		return list;
	}

	@Override
	public List<Comment> listComment(String processInstanceId) {
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
		List<Comment> comments=new ArrayList<>();
		for(HistoricActivityInstance task:list) {
			String taskId = task.getTaskId();
			comments.addAll(taskService.getTaskComments(taskId));
		}
		return comments;
	}

	@Override
	@Transactional
	public void saveComment(String taskId, String comment, Integer userId, String processInstanceId) {
		Authentication.setAuthenticatedUserId(userId+"");
		 taskService.addComment(taskId, processInstanceId, comment);
	}

	@Override
	public Result complateTask(String taskId,String processDefintionId, String comment, Integer userId, Boolean flag) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		task.getProcessInstanceId();
		this.saveComment(taskId, comment, userId, task.getProcessInstanceId());
		if(flag) {
			
		}else {
//			sysFlowFormMapper.getByProInsId(task.getProcessInstanceId());
//			SysWorkflowExample example=new SysWorkflowExample();
//			example.createCriteria().andProDefIdEqualTo(task.getProcessInstanceId());
//			sysWorkflowMapper.selectByExample(example);
		}
		
		return null;
	}

	@Override
	public List<Map<String, Object>> listByUsers(String id, String processDefinitionKey) {
		if(StringUtils.isAnyBlank(id,processDefinitionKey)) {
			return null;
		}
		List<Task> list = taskService.createTaskQuery().taskCandidateUser(id)
				.processDefinitionKey(processDefinitionKey)
				.list();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<Map<String,Object>> params=new ArrayList<>();
		for(Task task:list) {
			Map<String,Object> param=new HashMap<>();
			param.put("id",task.getId());
			param.put("name", task.getName());
			params.add(param);
		}
		return params;
	}

}
