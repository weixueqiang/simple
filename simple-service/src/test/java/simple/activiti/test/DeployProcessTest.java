package simple.activiti.test;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-activiti.xml"})
public class DeployProcessTest {

	@Resource
	private RepositoryService repositoryService;
	
	@Test
	public void deploy() {
		repositoryService.createDeployment().name("请假带驳回")
							.addClasspathResource("diagrams/LeaveBill.bpmn")
							.addClasspathResource("diagrams/LeaveBill.png")
							.deploy();
	}
}
