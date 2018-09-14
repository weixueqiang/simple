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

import com.jo.dy.ot.dao.LeaveBillMapper;
import com.jo.dy.ot.dao.SysWorkflowMapper;
import com.jo.dy.ot.dao.SysWorkflowStepMapper;
import com.jo.dy.ot.entity.LeaveBill;
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
	@Resource
	private SysWorkflowMapper sysWorkflowMapper;
	@Resource
	private LeaveBillMapper leaveBillMapper;

	@Test
	public void batchSave() {
		LeaveBill leaveBill=new LeaveBill();
		leaveBill.setDayTime(2);
		leaveBill.setProDefId("122");
		leaveBill.setReason("nothing");
		leaveBill.setUserId(12);
		leaveBill.setStatus("normal");
		Integer save = leaveBillMapper.save(leaveBill);
		System.out.println("----->>>>>>>>: "+save);
		System.out.println("----->>>>>>>>: "+leaveBill.getId());
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
		String deployId = "132501";
		String key = "_e5c704d0-46e1-489b-a7cb-53f47fa7e4ef" + ".";
		String resourceName = key + "bpmn";
		String resourceName2 = key + key + "png";
		InputStream bpmnIn = repositoryService.getResourceAsStream(deployId, resourceName);
		InputStream pngIn = repositoryService.getResourceAsStream(deployId, resourceName2);
		FileUtils.copyInputStreamToFile(bpmnIn, new File("C:/img/" + resourceName));
		FileUtils.copyInputStreamToFile(pngIn, new File("C:/img/" + resourceName2));
	}

	@Test
	public void simpleSave() {
		String stepArr = "[{\"usersId\":\"${userId}\",\"type\":2,\"seq\":0},{\"usersId\":\"9528\",\"type\":2,\"seq\":1},"
				+ "{\"usersId\":\"9529\",\"type\":2,\"seq\":2}]";
		SysWorkflow model = new SysWorkflow();
		model.setContent("没啥好描述的>>>>>))");
		model.setName("自定义的并行>>");
		String key = "_"+UUID.randomUUID().toString();
		model.setProcessKey(key);
		System.out.println(key + "------>>>>>>\n");
		sysWorkflowService.simpleSave(model, stepArr);
	}

	String processDefinitionKey = "parallel_";
	String taskId = "120011";

	@Test
	public void simpleStart() {
		runtimeService.startProcessInstanceByKey(processDefinitionKey);
	}

	@Test
	public void complate() {
		Map<String, Object> map = new HashMap<>();
		map.put("flag", false);
		taskService.complete(taskId, map);
		taskService.complete("120014", map);
		
	}
	@Test
	public void deleteInstance() {
		runtimeService.deleteProcessInstance("137501", "nothing ");
	}
	
	
	
}
