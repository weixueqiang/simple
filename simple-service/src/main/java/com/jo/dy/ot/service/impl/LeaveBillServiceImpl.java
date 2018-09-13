package com.jo.dy.ot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
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
import com.jo.dy.ot.service.BasicProcess;
import com.jo.dy.ot.service.LeaveBillService;
import com.jo.dy.ot.service.ProcessService;
import com.jo.dy.ot.util.BeanUtils;
import com.jo.dy.ot.util.Result;
import com.jo.dy.ot.util.SpringUtils;

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

	private static String processDefinitionKey = "LeaveBill";
	private static String businessKey = "leaveBillService";

	private String getName() {
		Service annotation = this.getClass().getAnnotation(Service.class);
		if (annotation != null) {
			return annotation.value();
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result save(LeaveBill leaveBill) {// 保存即启动流程
		Result result = new Result();
		String processKey = this.getProcessKey(null);
		if (StringUtil.isEmpty(processKey)) {
			result.fail("请先配置流程!");
			return result;
		}
		leaveBill.setStatus(StatusEnum.BE_SUBMIT.name());
		leaveBillMapper.insertSelective(leaveBill);
		Map<String, Object> params = new HashMap<>();
		params.put("userId", leaveBill.getUserId() + "");
		params.put("serviceName", getName());
		params.put("businessId", leaveBill.getId());
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey, params);
		// leaveBill.setProDefId(pi.getProcessInstanceId());
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result submitLeave(Integer id, Integer userId) {

		return null;
	}

	@Override
	public List<LeaveBill> list(int i) {
		LeaveBillExample example = new LeaveBillExample();
		example.createCriteria().andUserIdEqualTo(i);
		return leaveBillMapper.selectByExample(example);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateByKey(LeaveBill leaveBill) {
		leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
	}

	@Override
	public Result getTask(String taskId) {
		Result result = new Result();
		if (StringUtil.isEmpty(taskId)) {
			result.fail("任务id为空");
			return result;
		}
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		// String processDefinitionId=task.getProcessDefinitionId();
		// List<String> listFlow =
		// processService.listFlow(processInstanceId,processDefinitionId);
		// result.putData("listFlow", listFlow);
		String processInstanceId = task.getProcessInstanceId();
		String executionId= task.getExecutionId();
		Integer id=runtimeService.getVariable(executionId, "businessId", Integer.class);
		String serviceName=runtimeService.getVariable(executionId, "serviceName", String.class);
//		ProcessInstance singleResult = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
//				.singleResult();
//		Map<String, Object> processVariables = singleResult.getProcessVariables();
//		
//		Map<String, Object> params = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
//				.singleResult().getProcessVariables();
//		Integer id = (Integer) params.get("businessId");
//		String serviceName = (String) params.get("serviceName");
		BasicProcess bean = SpringUtils.getBean(serviceName);
		Object data = bean.get(id);
		result.setData(data);
		List<Comment> listComment = processService.listComment(processInstanceId);
		result.putData("listComment", listComment);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result complate(String taskId, Integer id, String comment, String condition, Integer userId) {
		LeaveBill leaveBill = leaveBillMapper.selectByPrimaryKey(id);
		processService.saveComment(taskId, comment, userId, leaveBill.getProDefId());
		Map<String, Object> variables = new HashMap<>();
		variables.put("condition", condition);
		taskService.complete(taskId, variables);
		ProcessInstance instance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(leaveBill.getProDefId()).singleResult();
		if (instance == null) {
			leaveBill.setStatus(StatusEnum.ACCPECT.name());
		} else {
			leaveBill.setStatus(StatusEnum.APPLY.name());
		}
		leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
		return new Result();
	}

	@Override
	public String getProcessKey(String customeId) {
		return sysWorkflowMapper.getProcessKey(getName());
	}

	@Override
	public LeaveBill get(Integer id) {
		return leaveBillMapper.selectByPrimaryKey(id);
	}

}
