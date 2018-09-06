package com.jo.dy.ot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jo.dy.ot.dao.LeaveMapper;
import com.jo.dy.ot.entity.Leave;
import com.jo.dy.ot.entity.LeaveExample;
import com.jo.dy.ot.process.service.ProcessService;
import com.jo.dy.ot.service.LeaveService;
import com.jo.dy.ot.util.Result;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {

	@Resource
	private LeaveMapper leaveMapper;
	
	@Resource
	private ProcessService processService;
	
	@Override
	public Result save(Leave leave) {
		leaveMapper.insertSelective(leave);
		Map<String, Object> variables=new HashMap<>();
		variables.put("userId", leave.getUserId());
		processService.startProcess("leave", variables);
		return null;
	}

	@Override
	public List<Leave> listByUser(Integer id) {
		LeaveExample example=new LeaveExample();
		example.createCriteria().andUserIdEqualTo(id);
		return leaveMapper.selectByExample(example);
	}

}
