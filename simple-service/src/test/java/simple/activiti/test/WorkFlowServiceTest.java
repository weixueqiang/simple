package simple.activiti.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jo.dy.ot.dao.SysWorkflowStepMapper;
import com.jo.dy.ot.entity.SysWorkflow;
import com.jo.dy.ot.entity.SysWorkflowStep;
import com.jo.dy.ot.service.SysWorkflowService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml",
		"classpath:spring/spring-activiti.xml" })
public class WorkFlowServiceTest {

	@Resource
	private SysWorkflowService sysWorkflowService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private SysWorkflowStepMapper sysWorkflowStepMapper;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;

	@Test
	@Transactional
	public void batchSave() {
		List<SysWorkflowStep> sysWorkflowSteps=new ArrayList<>();
		for (int i = 1; i < 3; i++) {
			SysWorkflowStep step=new SysWorkflowStep();
			step.setAssignss("ann_"+i);
			step.setCreateTime(new Date());
			step.setRoleId(""+i);
			step.setType(1);
			step.setUsersId("usersId"+i);
			step.setWorkflowId(12L);
			step.setId(Long.valueOf(i+""));
			sysWorkflowSteps.add(step);
		}
//		sysWorkflowStepMapper.batchCreate(sysWorkflowSteps);
		long start=System.currentTimeMillis();
		sysWorkflowStepMapper.batchUpdate(sysWorkflowSteps);
		long end=System.currentTimeMillis();
		System.out.println("耗时:"+(start-end));
	}
	
	
	@Test
	public void parallerySave() {
		String stepArr = "[{\"id\":123,\"rolePkno\":\"123\",\"type\":1}]";
		SysWorkflow model = new SysWorkflow();
		model.setContent("没啥好描述的");
		model.setName("自定义的");
		sysWorkflowService.save(model, stepArr);
	}

	@Test
	public void loadResource() throws IOException {
		String deployId = "117501";
		String key=""+".";
		String resourceName = key+"bpmn";
		String resourceName2 =key+key+"png";
		InputStream bpmnIn = repositoryService.getResourceAsStream(deployId, resourceName);
		InputStream pngIn = repositoryService.getResourceAsStream(deployId, resourceName2);
		FileUtils.copyInputStreamToFile(bpmnIn, new File("C:/img/" + resourceName));
		FileUtils.copyInputStreamToFile(pngIn, new File("C:/img/" + resourceName2));
	}

	@Test
	public void simpleSave() {
		String stepArr = "[{\"id\":12345,\"usersId\":\"cc\",\"type\":3,\"seq\":0}]";
		SysWorkflow model = new SysWorkflow();
		model.setContent("没啥好描述的>>>>>))");
		model.setName("自定义的并行>>");
		String key = UUID.randomUUID().toString();
		model.setProcessKey(key);
		System.out.println(key+"------>>>>>>\n");
		sysWorkflowService.simpleSave(model, stepArr);
	}

	String processDefinitionKey="parallel_";
	String taskId="120008";
	@Test
	public void simpleStart() {
		runtimeService.startProcessInstanceByKey(processDefinitionKey);
	}
	
	@Test
	public void complate() {
		Map<String, Object> map=new HashMap<>();
		map.put("flag", false);
		taskService.complete(taskId,map);
	}
}
