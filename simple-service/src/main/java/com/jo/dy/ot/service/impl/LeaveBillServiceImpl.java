package com.jo.dy.ot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.util.StringUtil;
import com.jo.dy.ot.dao.LeaveBillMapper;
import com.jo.dy.ot.dao.SysFlowFormMapper;
import com.jo.dy.ot.dao.SysWorkflowMapper;
import com.jo.dy.ot.entity.LeaveBill;
import com.jo.dy.ot.entity.LeaveBillExample;
import com.jo.dy.ot.entity.SysFlowForm;
import com.jo.dy.ot.entity.SysFlowFormExample;
import com.jo.dy.ot.entity.SysWorkflow;
import com.jo.dy.ot.enums.StatusEnum;
import com.jo.dy.ot.service.LeaveBillService;
import com.jo.dy.ot.service.ProcessService;
import com.jo.dy.ot.util.Result;
@Service("leaveBillService")
public class LeaveBillServiceImpl implements LeaveBillService {

	@Resource
	private LeaveBillMapper leaveBillMapper;
	@Resource
	private ProcessService processService;
	@Resource
	private SysFlowFormMapper sysFlowFormMapper;
	@Resource
	private SysWorkflowMapper sysWorkflowMapper;
	
	@Resource
	private TaskService taskService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	

	private static String processDefinitionKey="LeaveBill";
	private static String businessKey="leaveBillService";
	
	private String getName() {
		Service annotation = this.getClass().getAnnotation(Service.class);
		if(annotation!=null) {
			return annotation.value();
		}
		return null;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Result save(LeaveBill leaveBill) {//保存即启动流程
		Result result = new Result();
		sysWorkflowMapper.getProcessKey(getName());
		String processDefinitionId=this.getProcessDefinitionId(null);
		if(StringUtil.isEmpty(processDefinitionId)) {
			result.fail("请先配置流程!");
			return result;
		}
		leaveBill.setStatus(StatusEnum.BE_SUBMIT.name());
		int id = leaveBillMapper.insertSelective(leaveBill);
		Map<String, Object> params=new HashMap<>();
		params.put("userId", leaveBill.getUserId());
		params.put("serviceName", getName());
		params.put("businessId", id);
		ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinitionId, params);
//		leaveBill.setProDefId(pi.getProcessInstanceId());
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Result submitLeave(Integer id, Integer userId) {
	
		return null;
	}

	@Override
	public List<LeaveBill> list(int i) {
		LeaveBillExample example=new LeaveBillExample();
		example.createCriteria().andUserIdEqualTo(i);
		return leaveBillMapper.selectByExample(example);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateByKey(LeaveBill leaveBill) {
		leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
	}

	@Override
	public Result getTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		LeaveBillExample example=new LeaveBillExample();
		example.createCriteria().andProDefIdEqualTo(processInstanceId);
		List<LeaveBill> list = leaveBillMapper.selectByExample(example);
		LeaveBill leaveBill = list.get(0);
		Result result = new Result();
		result.setData(leaveBill);
		String processDefinitionId=task.getProcessDefinitionId();
		List<String> listFlow = processService.listFlow(processInstanceId,processDefinitionId);
		result.putData("listFlow", listFlow);
		List<Comment> listComment = processService.listComment(processInstanceId);
		result.putData("listComment", listComment);
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Result complate(String taskId, Integer id, String comment, String condition, Integer userId) {
		LeaveBill leaveBill = leaveBillMapper.selectByPrimaryKey(id);
		processService.saveComment(taskId,comment,userId,leaveBill.getProDefId());
		Map<String, Object> variables=new HashMap<>();
		variables.put("condition", condition);
		taskService.complete(taskId, variables);
		ProcessInstance instance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(leaveBill.getProDefId()).singleResult();
		if(instance==null) {
			leaveBill.setStatus(StatusEnum.ACCPECT.name());
		}else {
			leaveBill.setStatus(StatusEnum.APPLY.name());
		}
		leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
		return new Result();
	}

	@Override
	public String getProcessDefinitionId(String customeId) {
		SysFlowFormExample example=new SysFlowFormExample();
		example.createCriteria().andServiceNameEqualTo(getName());
		List<SysFlowForm> sysFlowForms = sysFlowFormMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(sysFlowForms)) {
			return null;
		}
		//理论只有一个,自己添加的
		SysFlowForm sysFlowForm=sysFlowForms.get(0);
		SysWorkflow sysWorkflow = sysWorkflowMapper.selectByPrimaryKey(sysFlowForm.getWorkflowId());
		if(sysWorkflow==null) {
			return null;
		}
		return sysWorkflow.getProDefId();
	}

}
