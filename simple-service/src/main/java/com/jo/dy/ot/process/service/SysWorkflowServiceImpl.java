package com.jo.dy.ot.process.service;

import java.util.*;

import javax.annotation.Resource;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.jo.dy.ot.entity.SysWorkflow;
import com.jo.dy.ot.entity.SysWorkflowStep;
import com.jo.dy.ot.service.SysWorkflowService;

import com.jo.dy.ot.util.Result;

@Service("sysWorkflowService")
public class SysWorkflowServiceImpl implements SysWorkflowService {

	@Resource
	private RepositoryService repositoryService;

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result simpleSave(SysWorkflow model, String steps) {
		List<SysWorkflowStep> sysWorkflowSteps = JSONArray.parseArray(steps, SysWorkflowStep.class);
		BpmnModel bpmnModel = new BpmnModel();
		Process process = new Process();
		bpmnModel.addProcess(process);
		process.setId(model.getProcessKey());
		process.setName(model.getName());
		process.setDocumentation(model.getContent());
		process.addFlowElement(createStartEvent());
		for (int i = 0; i < sysWorkflowSteps.size(); i++) {
			SysWorkflowStep sysWorkflowStep = sysWorkflowSteps.get(i);
			if (sysWorkflowStep.getType() == 3) {// 普通
				process.addFlowElement(createAssigneeTask("userTask" + i, "任务节点" + i, sysWorkflowStep.getAssignss()));
			}else if(sysWorkflowStep.getType() == 2) {//或签
				List<String> userIds = getUserIds(sysWorkflowStep);
				process.addFlowElement(createUserTask("userTask" + i, "任务节点" + i,userIds));
			}else if(sysWorkflowStep.getType() == 1) {//会签
				process.addFlowElement(createParallelGateway("parallerl_fork"+i, "并行开始"+i));
				List<String> userIds = getUserIds(sysWorkflowStep);
				for (int j = 0; j < userIds.size(); j++) {
					process.addFlowElement(createAssigneeTask("userTask"+i+"_"+j, "任务节点"+i+"_"+j, userIds.get(j)));
				}
				process.addFlowElement(createParallelGateway("parallerl_join"+i, "并行结束"+i));
			}
		}
		process.addFlowElement(createEndEvent());
		for (int i = 0; i < sysWorkflowSteps.size(); i++) {
			SysWorkflowStep sysWorkflowStep = sysWorkflowSteps.get(i);
			List<String> userIds = getUserIds(sysWorkflowStep);
			if(sysWorkflowStep.getType()==1) {//当前是否为并行
				if(i==0) {
					process.addFlowElement(createSequenceFlow("startEvent", "parallerl_fork" + i, "", ""));
				}else {
					if(sysWorkflowSteps.get(i-1).getType()==1) {
						process.addFlowElement(createSequenceFlow("parallerl_join" + (i-1), "parallerl_fork"+i, "", ""));
					}else {
						process.addFlowElement(createSequenceFlow("userTask" + (i-1), "parallerl_fork"+i, "", "${flag}"));
					
					}
				}
				for (int j = 0; j < userIds.size(); j++) {
					process.addFlowElement(createSequenceFlow("parallerl_fork" + i, "userTask" + i+"_"+j, "", ""));
					process.addFlowElement(createSequenceFlow("userTask" + i+"_"+j, "parallerl_join"+i, "", "${flag}"));
				}
				
				if(i==sysWorkflowSteps.size()-1) {
					process.addFlowElement(createSequenceFlow("parallerl_join" + i, "endEvent", "", ""));
				}
			}else {
				if(i==0) {
					process.addFlowElement(createSequenceFlow("startEvent", "userTask" + i, "", ""));
				}else {
					if(sysWorkflowSteps.get(i-1).getType()==1) {
						process.addFlowElement(createSequenceFlow("parallerl_join" + (i-1), "userTask"+i, "", ""));
					}else {
						process.addFlowElement(createSequenceFlow("userTask" + (i-1), "userTask"+i, "", "${flag}"));
					
					}
				}
				if(i==sysWorkflowSteps.size()-1) {
					process.addFlowElement(createSequenceFlow("userTask" + i, "endEvent", "", ""));
				}
			}
		}
		new BpmnAutoLayout(bpmnModel).execute();
		repositoryService.createDeployment().addBpmnModel(process.getId() + ".bpmn", bpmnModel).name(process.getName())
				.deploy();

		return null;
	}

	

	private List<String> getUserIds(SysWorkflowStep sysWorkflowStep){
		List<String> list = new ArrayList<String>();
		String role = sysWorkflowStep.getRoleId();
		String usersId = sysWorkflowStep.getUsersId();
		if(StringUtils.isBlank(role) && StringUtils.isBlank(usersId)) {
			return list;
		}
		if(StringUtils.isNotBlank(usersId)) {
			String[] split = usersId.split(",");
			for(String str:split) {
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