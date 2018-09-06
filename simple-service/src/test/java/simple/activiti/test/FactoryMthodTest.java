package simple.activiti.test;


import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class FactoryMthodTest {

	@Resource
	private TaskService taskService;
	
	@Test
	public void run() {
		System.out.println(taskService);
	}
	
	
	
}
