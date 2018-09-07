package com.jo.dy.ot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jo.dy.ot.dao.LeaveBillMapper;
import com.jo.dy.ot.entity.LeaveBill;
import com.jo.dy.ot.entity.LeaveBillExample;
import com.jo.dy.ot.enums.StatusEnum;
import com.jo.dy.ot.process.service.ProcessService;
import com.jo.dy.ot.service.LeaveBillService;
import com.jo.dy.ot.util.Result;
@Service("leaveBillService")
public class LeaveBillServiceImpl implements LeaveBillService {

	@Resource
	private LeaveBillMapper leaveBillMapper;
	
	@Resource
	private ProcessService processService;
	
	@Resource
	private TaskService taskService;

	private static String processDefinitionKey="LeaveBill";
	private static String businessKey="leaveBillService";
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Result save(LeaveBill leaveBill) {
		leaveBillMapper.insertSelective(leaveBill);
		return null;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Result submitLeave(Integer id, Integer userId) {
		LeaveBill record=new LeaveBill();
		record.setId(id);
		record.setStatus(StatusEnum.APPLY.name());
		leaveBillMapper.updateByPrimaryKeySelective(record);
		Map<String, Object> variables=new HashMap<>();
		variables.put("userId", userId);
		String businessKeys=businessKey+"."+id;
		processService.startProcess(processDefinitionKey, businessKeys, variables);
		List<Task> list = taskService.createTaskQuery().processInstanceBusinessKey(businessKeys).list();
		for(Task task:list) {
			processService.complateTask(task.getId());
		}
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

}
