package com.jo.dy.ot.redis.test;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.model.PushModel;
import com.jo.dy.ot.redis.SimpleDoWorkImpl;
import com.jo.dy.ot.redis.dao.RedisDao;
import com.jo.dy.ot.util.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-redis.xml", "classpath:spring/spring-service.xml",
		"classpath:spring/spring-dao.xml", "classpath:spring/spring-activiti.xml" })
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
		user.setUsername("z1s");
		redisDao.save("user", user);
		redisDao.convertAndSend(Constants.REDIS_CHANNEL, Constants.REDIS_CHANNEL);
	}

	@Test
	public void get() {
		System.out.println(pushModelTest2());
		System.out.println("method...over2");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String pushModelTest() {
		PushModel pushModel = new PushModel();
		Set<String> set = new HashSet<>();
		set.add("zhangsan");
		set.add("lisi");
		pushModel.setReceiverSet(set);
		SimpleDoWorkImpl simpleDoWorkImpl = new SimpleDoWorkImpl();
		simpleDoWorkImpl.setPushModel(pushModel);
		redisDao.save(Constants.REDIS_CHANNEL, simpleDoWorkImpl);
		redisDao.convertAndSend(Constants.REDIS_CHANNEL, Constants.REDIS_CHANNEL);
		return "ok";
	}

	public String pushModelTest2() {
		for (int i = 0; i < 10; i++) {
			PushModel pushModel = new PushModel();
			Set<String> set = new HashSet<>();
			set.add("zhangsan_" + i);
			set.add("lisi_" + i);
			pushModel.setReceiverSet(set);
			SimpleDoWorkImpl simpleDoWorkImpl = new SimpleDoWorkImpl();
			simpleDoWorkImpl.setPushModel(pushModel);
			redisDao.save(Constants.REDIS_CHANNEL, simpleDoWorkImpl);
			redisDao.convertAndSend(Constants.REDIS_CHANNEL, Constants.REDIS_CHANNEL);
		}
		return "ok";
	}

}
