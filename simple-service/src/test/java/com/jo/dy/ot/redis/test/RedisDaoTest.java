package com.jo.dy.ot.redis.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.redis.dao.RedisDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-redis.xml","classpath:spring/spring-service.xml","classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

	@Resource
	private RedisDao redisDao;
	
	@Test
	public void test() {
		System.out.println(redisDao);
	}
	
	@Test
	public void save() {
		User user = new User();
		user.setId(12);
		user.setPassword("password");
		user.setSalt("haha");
		user.setUsername("zs");
		redisDao.save("user", user);
		
	}
	
	@Test
	public void get() {
		User object = (User) redisDao.get("user");
		System.out.println(object.getUsername());
	}
	
}
