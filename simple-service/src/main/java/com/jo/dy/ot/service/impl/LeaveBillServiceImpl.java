package com.jo.dy.ot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;
import com.jo.dy.ot.dao.LeaveBillMapper;
import com.jo.dy.ot.dao.SysFlowFormMapper;
import com.jo.dy.ot.dao.SysWorkflowMapper;
import com.jo.dy.ot.entity.LeaveBill;
import com.jo.dy.ot.entity.LeaveBillExample;
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
		leaveBillMapper.save(leaveBill);
		// 启动流程
		processService.startProcess(processKey, leaveBill.getUserId() + "", getName(), leaveBill.getId());
		return result;
	}

	@Override
	public List<LeaveBill> list(Integer userId) {
		LeaveBillExample example = new LeaveBillExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return leaveBillMapper.selectByExample(example);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateByKey(LeaveBill leaveBill) {
		leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
	}

	@Override
	public String getProcessKey(String customeId) {
		return sysWorkflowMapper.getProcessKey(getName());
	}

	@Override
	public LeaveBill get(Integer id) {
		return leaveBillMapper.selectByPrimaryKey(id);
	}

	@Override
	public void dealBusiness(Integer id, String flag) {
		LeaveBill record = new LeaveBill();
		record.setId(id);
		record.setStatus(flag);
		leaveBillMapper.updateByPrimaryKeySelective(record);
	}

}
