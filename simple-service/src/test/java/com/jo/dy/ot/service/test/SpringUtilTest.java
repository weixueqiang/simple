package com.jo.dy.ot.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jo.dy.ot.service.UserService;
import com.jo.dy.ot.service.impl.SpringUtilGetBean;
import com.jo.dy.ot.service.impl.UserServiceImpl;
import com.jo.dy.ot.util.SpringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test/*.xml")
public class SpringUtilTest {

	@Test
	public void test() {
		UserService userService = SpringUtils.getBean("userService");
		System.out.println(userService);
	}
	
	@Test
	public void test2() {
		UserService userService = SpringUtils.getBean(UserService.class);
		System.out.println(userService);
	}
	
	@Test
	public void test3() {
		SpringUtilGetBean userService = SpringUtils.getBean("springUtilGetBean");
		System.out.println(userService);
		UserServiceImpl impl=SpringUtils.getBean("userService");
		System.out.println(impl);
	}
}
