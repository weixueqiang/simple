package com.jo.dy.ot.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.viewer.LogFactor5InputDialog;
import org.junit.runners.Parameterized.UseParametersRunnerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

import com.jo.dy.ot.entity.LeaveBill;
import com.jo.dy.ot.service.LeaveBillService;
import com.jo.dy.ot.service.ProcessService;
import com.jo.dy.ot.util.MyUtils;
import com.jo.dy.ot.util.Result;

@RestController
@RequestMapping("/leave")
public class LeaveController {

	private Logger logger = Logger.getLogger(LeaveController.class);

	@Resource
	private LeaveBillService leaveBillService;
	@Resource
	private ProcessService processService;

	@RequestMapping("save")
	public Result save(LeaveBill leaveBill) {
		Result result = new Result();
		leaveBill.setUserId(MyUtils.getUser().getId());
		leaveBillService.save(leaveBill);
		return result;
	}

	@RequestMapping("list")
	public Result list() {
		Result result = new Result();
		result.setData(leaveBillService.list(MyUtils.getUser().getId()));
		return result;
	}

	@RequestMapping("listTask")
	public Result listTask() {
		Result result = new Result();
		result.setData(processService.listByAssignee(MyUtils.getUser().getId()+"",
				leaveBillService.getProcessDefinitionId(null)));
		return result;
	}

	@RequestMapping("getTask")
	public Result getTask(String taskId) {
		Result result = new Result();
		result = leaveBillService.getTask(taskId);
		return result;
	}

	@RequestMapping("/complate")
	public Result complate(String taskId, String processDefintionId, String comment, String condition,Boolean flag) {
		Result result = new Result();
//		result = leaveBillService.complate(taskId, id, comment, condition, MyUtils.getUser().getId());
		result=processService.complateTask(taskId, processDefintionId, comment,MyUtils.getUser().getId(),flag);
		
		return result;
	}

}
