package com.jo.dy.ot.service.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jo.dy.ot.service.UserService;
import com.jo.dy.ot.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml","classpath:spring/spring-dao.xml","classpath:spring/spring-activiti.xml"})
public class TractionalTest {

	@Resource(name="userService")
	private UserService userService;
	
	@Test
	public void saveNoTranction() {
		userService.saveNoTranction();
		System.out.println("00000=>\n");
	}
	
	@Test
	public void saveWithTranction() {
		userService.saveWithTranction();
	}
	
}
