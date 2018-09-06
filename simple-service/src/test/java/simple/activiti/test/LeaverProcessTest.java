package simple.activiti.test;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jo.dy.ot.entity.Leave;
import com.jo.dy.ot.enums.StatusEnum;
import com.jo.dy.ot.service.LeaveService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class LeaverProcessTest {

	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private LeaveService leaveService;
	
	@Test
	public void depoly() {
		Deployment deploy = repositoryService
						.createDeployment()
						.name("请假流程")
						.addClasspathResource("diagrams/leave.bpmn")
						.addClasspathResource("diagrams/leave.png")
						.deploy();
		System.out.println(deploy.getId());
		System.out.println(deploy.getName());
	}
	
	@Test
	public void leave() {
		Leave leave=new Leave(9527, "回家啦", StatusEnum.APPLY.toString(), 3);
		leaveService.save(leave);
		System.out.println("请假发起");
	}
	
}
