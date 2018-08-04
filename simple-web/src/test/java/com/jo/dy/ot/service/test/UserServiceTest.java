package com.jo.dy.ot.service.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class UserServiceTest {

	@Resource
	private UserService userService;
	
	@Test
	public void test() {
		System.out.println(userService);
	}
	
	@Test
	public void save() {
		User user =new User();
		user.setPassword("123456");
		user.setUsername("zhangsan");
		user.setSalt("salt1");
		userService.save(user );
	}
	
}
