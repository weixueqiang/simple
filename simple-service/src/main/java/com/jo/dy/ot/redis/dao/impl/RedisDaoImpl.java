package com.jo.dy.ot.redis.dao.impl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.jo.dy.ot.redis.dao.RedisDao;

@Component("redisDao")
public class RedisDaoImpl implements RedisDao {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	public void save(String key, Object value) {
		redisTemplate.opsForList().leftPush(key, value);
	}

	public Object get(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	@Override
	public void convertAndSend(String channel, Object value) {
		redisTemplate.convertAndSend(channel, value);
	}

	@Override
	public int getLinkLength(String key) {
		return redisTemplate.opsForList().size(key).intValue();
	}
}
