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

import com.github.pagehelper.util.StringUtil;
import com.jo.dy.ot.dao.SysFlowFormMapper;
import com.jo.dy.ot.dao.SysWorkflowMapper;
import com.jo.dy.ot.entity.SysWorkflowExample;
import com.jo.dy.ot.enums.StatusEnum;
import com.jo.dy.ot.service.BasicProcess;
import com.jo.dy.ot.service.ProcessService;
import com.jo.dy.ot.util.Result;
import com.jo.dy.ot.util.SpringUtils;

import simple.activiti.test.WorkFlowServiceTest;

@Component("processService")
public class ProcessServiceImpl implements ProcessService {

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
		if (pi2 == null) {// 任务结束
			String[] split = businessKey.split("\\.");

		}

	}

	@Override
	public List<Map<String, Object>> listByAssignee(String id, String processDefinitionKey) {
		if (StringUtils.isAnyBlank(id, processDefinitionKey)) {
			return null;
		}
		List<Task> list = taskService.createTaskQuery().taskAssignee(id).processDefinitionKey(processDefinitionKey)
				.list();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<Map<String, Object>> params = new ArrayList<>();
		for (Task task : list) {
			Map<String, Object> param = new HashMap<>();
			param.put("id", task.getId());
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
		List<String> list = new ArrayList<String>();
		if (CollectionUtils.isEmpty(pvmList)) {
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
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId).list();
		List<Comment> comments = new ArrayList<>();
		for (HistoricActivityInstance task : list) {
			String taskId = task.getTaskId();
			comments.addAll(taskService.getTaskComments(taskId));
		}
		return comments;
	}

	@Override
	@Transactional
	public void saveComment(String taskId, String comment, Integer userId, String processInstanceId) {
		Authentication.setAuthenticatedUserId(userId + "");
		taskService.addComment(taskId, processInstanceId, comment);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result complateTask(String taskId, String comment, Integer userId, Boolean flag) {
		Result result = new Result();
		if (StringUtils.isBlank(taskId)) {
			return result.fail("任务标识不能为空!");
		}
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {// 或签,组任务时可能出现被其它成员完成的情况
			return result.fail("任务已结束!");
		}
		try {
			if (StringUtils.isNoneBlank(comment)) {
				this.saveComment(taskId, comment, userId, task.getProcessInstanceId());
			}
			Integer id = runtimeService.getVariable(task.getExecutionId(), "businessId", Integer.class);
			String serviceName = runtimeService.getVariable(task.getExecutionId(), "serviceName", String.class);
			String status = null;// 具体业务对象的流程状态
			if (flag) {
				taskService.claim(taskId, userId + "");// 操作人员拾取任务
				Map<String, Object> variables = new HashMap<>();
				variables.put("flag", flag);
				taskService.complete(taskId, variables);
				ProcessInstance instance = runtimeService.createProcessInstanceQuery()
						.processInstanceId(task.getProcessInstanceId()).singleResult();
				// 执行实例为空标识任务完成
				status = instance == null ? StatusEnum.ACCPECT.name() : StatusEnum.APPLY.name();
			} else {// 不同意直接就删除执行实例了
				runtimeService.deleteProcessInstance(task.getProcessInstanceId(), "任务不通过");
				status = StatusEnum.FAIL.name();
			}
			// 获取具体的业务对象,并执行相应操作
			BasicProcess bean = SpringUtils.getBean(serviceName);
			bean.dealBusiness(id, status);
		} catch (Exception e) {
			e.printStackTrace();
			return result.fail("任务已完成或任务异常!");
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> listByUsers(String id, String processDefinitionKey) {
		if (StringUtils.isAnyBlank(id, processDefinitionKey)) {
			return null;
		}
		List<Task> list = taskService.createTaskQuery().taskCandidateUser(id)
				 .processDefinitionKey(processDefinitionKey)
				.list();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<Map<String, Object>> params = new ArrayList<>();
		for (Task task : list) {
			Map<String, Object> param = new HashMap<>();
			param.put("id", task.getId());
			param.put("name", task.getName());
			params.add(param);
		}
		return params;
	}

	@Override
	public ProcessInstance startProcess(String processKey, String userId, String name, Integer id) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("serviceName", name);
		params.put("businessId", id);
		return  runtimeService.startProcessInstanceByKey(processKey, params);
	}

	@Override
	public Result getTask(String taskId) {
		Result result = new Result();
		if (StringUtil.isEmpty(taskId)) {
			result.fail("任务id为空");
			return result;
		}
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			String processInstanceId = task.getProcessInstanceId();
			String executionId = task.getExecutionId();
			Integer id = runtimeService.getVariable(executionId, "businessId", Integer.class);
			String serviceName = runtimeService.getVariable(executionId, "serviceName", String.class);
			BasicProcess bean = SpringUtils.getBean(serviceName);
			result.setData(bean.get(id));
			result.putData("listComment", listComment(processInstanceId));
		} catch (Exception e) {
			e.printStackTrace();
			return result.fail("任务已结束");
		}
		return result;
	}

}
