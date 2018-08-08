package com.jo.dy.ot.redis.dao;

public interface RedisDao {

	void save(String key,Object value);
	
	Object get(String key);
	
	void convertAndSend(String channel,Object value);
}
