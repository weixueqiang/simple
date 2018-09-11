package simple.activiti.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jo.dy.ot.entity.SysWorkflow;
import com.jo.dy.ot.service.SysWorkflowService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml",
		"classpath:spring/spring-activiti.xml" })
public class WorkFlowServiceTest {

	@Resource
	private SysWorkflowService sysWorkflowService;
	@Resource
	private RepositoryService repositoryService;
	
	@Test
	public void parallerySave() {
		String stepArr="[{\"id\":123,\"rolePkno\":\"123\",\"type\":1}]";
		SysWorkflow model=new SysWorkflow();
		model.setContent("没啥好描述的");
		model.setName("自定义的");
		sysWorkflowService.save(model, stepArr);
	}
	
	@Test
	public void loadResource() throws IOException {
		String deployId="107501";
		String resourceName = "parallel_.bpmn";
		String resourceName2 = "parallel_.parallel_.png";
		InputStream bpmnIn = repositoryService.getResourceAsStream(deployId, resourceName);
		InputStream pngIn = repositoryService.getResourceAsStream(deployId, resourceName2);
		FileUtils.copyInputStreamToFile(bpmnIn, new File("C:/img/"+resourceName));
		FileUtils.copyInputStreamToFile(pngIn, new File("C:/img/"+resourceName2));
	}

	@Test
	public void simpleSave() {
		String stepArr="[{\"id\":123,\"assignss\":\"123\",\"type\":3},{\"id\":1234,\"usersId\":\"a1,a2,a3\",\"type\":1}]";
		SysWorkflow model=new SysWorkflow();
		model.setContent("没啥好描述的>>>>>))");
		model.setName("自定义的并行>>");
		model.setKey("parallel_");
		sysWorkflowService.simpleSave(model, stepArr);
	}
	
	
}
