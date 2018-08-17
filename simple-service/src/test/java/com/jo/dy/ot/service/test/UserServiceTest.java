package com.jo.dy.ot.service.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.service.UserService;
import com.jo.dy.ot.util.PageUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-redis.xml"})
public class UserServiceTest {

	@Resource
	private UserService userService;
	
	@Test
	public void test() {
		System.out.println(userService);
	}
	
	@Test
	public void save() {
		for (int i = 0; i <10; i++) {
			User user =new User();
			user.setPassword("123456"+i);
			user.setUsername("zhangsan"+i);
			user.setSalt("salt1"+i);
			userService.save(user );
		}
	}
	
	
	
	@Test
	public void pageTest() {
		PageUtils<User> page = new PageUtils<User>();
		page.setPage(5);
		page.setLimit(5);
		PageUtils<User> showPage = userService.showPage(page);
		System.out.println("...>>>>>>>>>\n");
		showPage.getData().forEach((e)->System.out.println(e));
		System.out.println("...>>>>>>>>>\n");
		System.out.println(showPage.isEmpty());
	}
}
