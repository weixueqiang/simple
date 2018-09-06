package simple.activiti.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class Helloword {

	@Resource
	private ProcessEngine processEngine;
	
	@Test
	public void run() {
		System.out.println(processEngine);
	}
	
	@Test
	public void depoly() {
		Deployment deploy = processEngine.getRepositoryService()
						.createDeployment()
						.name("整合部署测试")
						.addClasspathResource("diagrams/helloword.bpmn")
						.addClasspathResource("diagrams/helloword.png")
						.deploy();
		System.out.println(deploy.getId());
		System.out.println(deploy.getName());
	}
	
	@Test
	public void startProcess() {
		String processDefinitionKey="helloword";
		Map<String, Object> variables=new HashMap<>();
		variables.put("userId", "张三");
		ProcessInstance pi = processEngine.getRuntimeService()
						.startProcessInstanceByKey(processDefinitionKey, variables);
		System.out.println(pi.getProcessDefinitionId());
		System.out.println(pi.getProcessInstanceId());
	}
	
	@Test
	public void queryPersonalTask() {
		String assignee="张三";
		List<Task> list = processEngine.getTaskService()
						.createTaskQuery()
						.taskAssignee(assignee)
						.orderByExecutionId()
						.asc()
						.list();
		if(CollectionUtils.isEmpty(list)) {
			System.out.println("没有任务");
		}
		list.forEach((e)->System.out.println(e.getId()+" :"+e.getAssignee()));
	}
	
	@Test
	public void complateTask() {
		String taskId="7505";
		processEngine.getTaskService()
						.complete(taskId);
		System.out.println(taskId+" :任务完成");
	}
	
}
