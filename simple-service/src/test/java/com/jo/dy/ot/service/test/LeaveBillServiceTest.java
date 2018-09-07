package com.jo.dy.ot.service.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.jo.dy.ot.entity.LeaveBill;
import com.jo.dy.ot.enums.StatusEnum;
import com.jo.dy.ot.process.service.ProcessService;
import com.jo.dy.ot.service.LeaveBillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-activiti.xml"})
public class LeaveBillServiceTest {

	@Resource
	private LeaveBillService leaveBillService;
	
	@Resource
	private ProcessService processService;
	
	@Resource
	private TaskService taskService;
	
	@Resource
	private RuntimeService runtimeService;
	
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
		LeaveBill leaveBill=new LeaveBill(9527, "请假回家", StatusEnum.BE_SUBMIT.name(), 3);
		leaveBillService.save(leaveBill);
	}
	
	@Test
	public void list() {
		List<LeaveBill> list=leaveBillService.list(9527);
		System.out.println(list);
	}
	
	@Test
	public void submit() {
		Integer userId=9527;
		leaveBillService.submitLeave(2, userId);
	}
	
	@Test
	public void queryTask() {
		String taskId="20002";
		Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
		String executionId = t.getExecutionId();
		String processInstanceId = t.getProcessInstanceId();
		System.out.println(executionId);
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		System.out.println(pi.getBusinessKey());
//		String assignee=9528+"";
//		List<Task> listTask = processService.listTask(assignee);
//		if(CollectionUtils.isEmpty(listTask)) {
//			System.out.println("no task you");
//		}
//		for(Task task:listTask) {
//			System.out.println(task.getId());
//			System.out.println(task.getAssignee());
//		}
	}
	
	@Test
	public void complateTask() {
//		LeaveBill leaveBill=new LeaveBill();
//		leaveBill.setStatus(StatusEnum.APPLY.name());
//		leaveBill.setId(2);
//		leaveBillService.updateByKey(leaveBill);
		
		String taskId="17505";
		processService.complateTask(taskId);
	}
	
}
