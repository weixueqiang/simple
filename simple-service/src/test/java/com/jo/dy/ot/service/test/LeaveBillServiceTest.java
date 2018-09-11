package com.jo.dy.ot.service.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.jo.dy.ot.entity.LeaveBill;
import com.jo.dy.ot.enums.StatusEnum;
import com.jo.dy.ot.process.service.ProcessServiceImpl;
import com.jo.dy.ot.service.LeaveBillService;
import com.jo.dy.ot.service.ProcessService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml",
		"classpath:spring/spring-activiti.xml" })
public class LeaveBillServiceTest {

	@Resource
	private LeaveBillService leaveBillService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private ProcessService processService;

	@Resource
	private TaskService taskService;

	@Resource
	private RuntimeService runtimeService;
	@Resource
	private HistoryService historyService;
	@Resource
	private SqlSessionFactory sqlSessionFactory;

	@Resource
	private BasicDataSource dataSource;

	@Test
	public void getSession() throws SQLException {
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(" update leave_bill set status='NORMAL' where id=1");
		connection.close();
		System.out.println(connection);
	}

	@Test
	public void save() {
		LeaveBill leaveBill = new LeaveBill(null, 9527, "请假回家", StatusEnum.BE_SUBMIT.name(), 3, null);
		leaveBillService.save(leaveBill);
	}

	@Test
	public void listBill() {// 获取请假单列表
		List<LeaveBill> list = leaveBillService.list(9527);
		System.out.println(list);
	}

	@Test
	public void listFlow() {// 获取连接线的名称
		String processInstanceId = "32501";
		Task task = taskService.createTaskQuery().taskAssignee("9528").processInstanceId(processInstanceId)
				.singleResult();
		String processDefinitionId = task.getProcessDefinitionId();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
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
		if (pvmList != null && pvmList.size() > 0) {
			for (PvmTransition pvm : pvmList) {
				String name = (String) pvm.getProperty("name");
				if (StringUtils.isNotBlank(name)) {
					list.add(name);
				} else {
					list.add("默认提交");
				}
			}
		}
		System.out.println(list);
		System.out.println(bpmnModel);
	}

	@Test
	public void comment() {
		Authentication.setAuthenticatedUserId("9528");
		taskService.addComment("40002", "32501", "同意");
		Map<String,Object> map=new HashMap<>();
		map.put("condition", "批准");
		taskService.complete("40002",map);
	}
	
	@Test
	public void listCommet() {
		List<Comment> historyCommnets = new ArrayList<>();
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId("32501").list();
		for(HistoricActivityInstance task:list) {
			String taskId = task.getTaskId();
			historyCommnets.addAll(taskService.getTaskComments(taskId));
		}
		for(Comment com:historyCommnets) {
			System.out.println(com.getFullMessage());
		}
		
	}
	
	
	
	@Test
	public void submit() {
		Integer userId = 9527;
		leaveBillService.submitLeave(2, userId);
	}

	@Test
	public void queryTask() {

		String assignee = 9528 + "";
		String processDefinitionKey = "LeaveBill";
		List<Task> listTask = taskService.createTaskQuery().taskAssignee(assignee)
				.processDefinitionKey(processDefinitionKey).list();
		if (CollectionUtils.isEmpty(listTask)) {
			System.out.println("no task you");
		}
		for (Task task : listTask) {
			System.err.println(task.getId());
			System.err.println(task.getAssignee());
		}
	}

	@Test
	public void complateTask() {
		String taskId = "40002";
		taskService.complete(taskId);
	}

	@Test
	public void queryHistoryTask() {
		String taskId = "17505";
		HistoricTaskInstance singleResult = historyService.createHistoricTaskInstanceQuery().taskId(taskId)
				.singleResult();
		System.out.println(singleResult.getProcessInstanceId());
	}

	@Test
	public void deleteProcess() {
		repositoryService.deleteDeployment("30001", true);
	}
			
	
	@Test
	public void readTask() {

		String taskId = "27504";
		boolean isAllowed = true;

		if (isAllowed) {
			taskService.complete(taskId);
			HistoricTaskInstance instance = historyService.createHistoricTaskInstanceQuery().taskId(taskId)
					.singleResult();

			ProcessInstance in = runtimeService.createProcessInstanceQuery()
					.processDefinitionId(instance.getProcessInstanceId()).singleResult();
			HistoricProcessInstance ins = historyService.createHistoricProcessInstanceQuery()
					.processDefinitionId(instance.getProcessInstanceId()).singleResult();
			String businessKey = ins.getBusinessKey();
			String[] split = businessKey.split(":");
			LeaveBill leaveBill = new LeaveBill();
			leaveBill.setId(Integer.parseInt(split[1]));
			if (in == null) {
				leaveBill.setStatus(StatusEnum.NORMAL.name());
				leaveBillService.updateByKey(leaveBill);
			}
		} else {

		}
	}
	
	@Test
	public void finishTest() {
		Map<String, Object> params=new HashMap<>();
		params.put("flag", true);
//		runtimeService.startProcessInstanceByKey("simpleOr", params);
		taskService.complete("90003",params);
		
	}
	

}
