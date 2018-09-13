package com.jo.dy.ot.process.service;

import java.util.*;

import javax.annotation.Resource;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.jo.dy.ot.dao.SysWorkflowMapper;
import com.jo.dy.ot.dao.SysWorkflowStepMapper;
import com.jo.dy.ot.entity.SysWorkflow;
import com.jo.dy.ot.entity.SysWorkflowStep;
import com.jo.dy.ot.service.SysWorkflowService;

import com.jo.dy.ot.util.Result;

@Service("sysWorkflowService")
public class SysWorkflowServiceImpl implements SysWorkflowService {

	@Resource
	private RepositoryService repositoryService;
	@Resource
	private SysWorkflowMapper sysWorkflowMapper;
	@Resource
	private SysWorkflowStepMapper sysWorkflowStepMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result simpleSave(SysWorkflow model, String steps) {
		List<SysWorkflowStep> sysWorkflowSteps = JSONArray.parseArray(steps, SysWorkflowStep.class);
		BpmnModel bpmnModel = new BpmnModel();//创建模型
		Process process = new Process();//创建流程
		bpmnModel.addProcess(process);//将流程添加到模型
		process.setId(model.getProcessKey());//流程key
		process.setName(model.getName());//流程名称
		process.setDocumentation(model.getContent());//流程描述
		
		process.addFlowElement(createStartEvent());//创建开始节点
		createUserTask(sysWorkflowSteps, process);//创建任务节点
		process.addFlowElement(createEndEvent());//创建结束节点
		
		createFlowSequence(sysWorkflowSteps, process);//创建节点间的连线
		
		new BpmnAutoLayout(bpmnModel).execute();//生成模型
		//部署流程
		Deployment deploy = repositoryService.createDeployment().addBpmnModel(process.getId() + ".bpmn", bpmnModel)
				.name(process.getName()).deploy();
		//获取流程定义对象
		ProcessDefinition proDef = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId())
				.singleResult();
		model.setProDefId(proDef.getId());
		saveWorkflow(model, sysWorkflowSteps);//保存自定义的数据
		return null;
	}

	private void createFlowSequence(List<SysWorkflowStep> sysWorkflowSteps, Process process) {
		for (int i = 0; i < sysWorkflowSteps.size(); i++) {
			SysWorkflowStep sysWorkflowStep = sysWorkflowSteps.get(i);
			List<String> userIds = getUserIds(sysWorkflowStep);
			if (sysWorkflowStep.getType() == 1) {// 当前是否为并行
				if (i == 0) {
					process.addFlowElement(createSequenceFlow("startEvent", "parallerl_fork" + i, "", ""));
				} else {
					if (sysWorkflowSteps.get(i - 1).getType() == 1) {
						process.addFlowElement(
								createSequenceFlow("parallerl_join" + (i - 1), "parallerl_fork" + i, "", ""));
					} else {
						process.addFlowElement(
								createSequenceFlow("userTask" + (i - 1), "parallerl_fork" + i, "", "${flag}"));

					}
				}
				for (int j = 0; j < userIds.size(); j++) {
					process.addFlowElement(createSequenceFlow("parallerl_fork" + i, "userTask" + i + "_" + j, "", ""));
					process.addFlowElement(
							createSequenceFlow("userTask" + i + "_" + j, "parallerl_join" + i, "", "${flag}"));
				}

				if (i == sysWorkflowSteps.size() - 1) {
					process.addFlowElement(createSequenceFlow("parallerl_join" + i, "endEvent", "", ""));
				}
			} else {
				if (i == 0) {
					process.addFlowElement(createSequenceFlow("startEvent", "userTask" + i, "", ""));
				} else {
					if (sysWorkflowSteps.get(i - 1).getType() == 1) {
						process.addFlowElement(createSequenceFlow("parallerl_join" + (i - 1), "userTask" + i, "", ""));
					} else {
						process.addFlowElement(createSequenceFlow("userTask" + (i - 1), "userTask" + i, "", "${flag}"));

					}
				}
				if (i == sysWorkflowSteps.size() - 1) {
					process.addFlowElement(createSequenceFlow("userTask" + i, "endEvent", "", ""));
				}
			}
		}
	}

	private void createUserTask(List<SysWorkflowStep> sysWorkflowSteps, Process process) {
		for (int i = 0; i < sysWorkflowSteps.size(); i++) {
			SysWorkflowStep sysWorkflowStep = sysWorkflowSteps.get(i);
			List<String> userIds = getUserIds(sysWorkflowStep);
			if (sysWorkflowStep.getType() == 3) {// 普通
				process.addFlowElement(createAssigneeTask("userTask" + i, "任务节点" + i, sysWorkflowStep.getAssignss()));
			} else if (sysWorkflowStep.getType() == 2) {// 或签
				process.addFlowElement(createUserTask("userTask" + i, "任务节点" + i, userIds));
			} else if (sysWorkflowStep.getType() == 1) {// 会签
				process.addFlowElement(createParallelGateway("parallerl_fork" + i, "并行开始" + i));
				
				for (int j = 0; j < userIds.size(); j++) {
					process.addFlowElement(
							createAssigneeTask("userTask" + i + "_" + j, "任务节点" + i + "_" + j, userIds.get(j)));
				}
				process.addFlowElement(createParallelGateway("parallerl_join" + i, "并行结束" + i));
			}
		}
	}

	private void saveWorkflow(SysWorkflow model, List<SysWorkflowStep> sysWorkflowSteps) {
		int id = sysWorkflowMapper.insertSelective(model);
		for (SysWorkflowStep step : sysWorkflowSteps) {
			step.setWorkflowId(Long.valueOf(id + ""));
			step.setCreateTime(new Date());
			// sysWorkflowStepMapper.insertSelective(step);
		}
		sysWorkflowStepMapper.batchCreate(sysWorkflowSteps);
	}

	private List<String> getUserIds(SysWorkflowStep sysWorkflowStep) {
		List<String> list = new ArrayList<String>();
		String role = sysWorkflowStep.getRoleId();
		String usersId = sysWorkflowStep.getUsersId();
		if (StringUtils.isBlank(role) && StringUtils.isBlank(usersId)) {
			return list;
		}
		if (StringUtils.isNotBlank(usersId)) {
			String[] split = usersId.split(",");
			for (String str : split) {
				list.add(str);
			}
			return list;
		}
		list.add("9530");
		list.add("9531");
		list.add("9532");
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result save(SysWorkflow model, String stepArr) {

		return null;
	}

	// 任务节点-组
	protected UserTask createGroupTask(String id, String name, String candidateGroup) {
		List<String> candidateGroups = new ArrayList<String>();
		candidateGroups.add(candidateGroup);
		UserTask userTask = new UserTask();
		userTask.setName(name);
		userTask.setId(id);
		userTask.setCandidateGroups(candidateGroups);
		return userTask;
	}

	// 任务节点-用户
	protected UserTask createUserTask(String id, String name, String userPkno) {
		List<String> candidateUsers = new ArrayList<String>();
		candidateUsers.add(userPkno);
		UserTask userTask = new UserTask();
		userTask.setName(name);
		userTask.setId(id);
		userTask.setCandidateUsers(candidateUsers);
		return userTask;
	}

	// 任务节点-锁定者
	protected UserTask createAssigneeTask(String id, String name, String assignee) {
		UserTask userTask = new UserTask();
		userTask.setName(name);
		userTask.setId(id);
		userTask.setAssignee(assignee);
		return userTask;
	}

	// 连线
	protected SequenceFlow createSequenceFlow(String from, String to, String name, String conditionExpression) {
		SequenceFlow flow = new SequenceFlow();
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		flow.setName(name);
		if (StringUtils.isNotEmpty(conditionExpression)) {
			flow.setConditionExpression(conditionExpression);
		}
		return flow;
	}

	// 排他网关
	protected ExclusiveGateway createExclusiveGateway(String id, String name) {
		ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
		exclusiveGateway.setId(id);
		exclusiveGateway.setName(name);
		return exclusiveGateway;
	}

	// 并行网关
	protected ParallelGateway createParallelGateway(String id, String name) {
		ParallelGateway gateway = new ParallelGateway();
		gateway.setId(id);
		gateway.setName(name);
		return gateway;
	}

	// 开始节点
	protected StartEvent createStartEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEvent");
		return startEvent;
	}

	// 结束节点
	protected EndEvent createEndEvent() {
		EndEvent endEvent = new EndEvent();
		endEvent.setId("endEvent");
		return endEvent;
	}

	private FlowElement createUserTask(String id, String name, List<String> userIds) {
		UserTask userTask = new UserTask();
		userTask.setName(name);
		userTask.setId(id);
		userTask.setCandidateUsers(userIds);
		return userTask;
	}

}