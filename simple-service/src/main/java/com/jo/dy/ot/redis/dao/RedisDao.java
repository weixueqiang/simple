package com.jo.dy.ot.redis.dao;

public interface RedisDao {

	void save(String key, Object value);

	Object get(String key);

	/**
	 * 订阅推送
	 * 
	 * @date 2018年9月19日 下午3:36:23
	 * @author weixueqiang
	 */
	void convertAndSend(String channel, Object value);

	int getLinkLength(String key);

}
