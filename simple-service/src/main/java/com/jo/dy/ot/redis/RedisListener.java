package com.jo.dy.ot.redis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.jo.dy.ot.redis.dao.RedisDao;
import com.jo.dy.ot.util.Constants;

@Component
public class RedisListener implements MessageListener{

	private static Logger logger=Logger.getLogger(RedisListener.class);
	
	@Resource
	private RedisDao redisDao;
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		logger.info("redis监听到.....");
		if (message == null || message.getChannel() == null) {
			return;
		}
		String chnanel=new String(message.getChannel());
		String key = (String) SerializationUtils.deserialize(message.getBody());
		logger.info("redis监听到频道为:"+chnanel+"的信息");
		if(Constants.REDIS_CHANNEL.equals(chnanel)) {
			Object object = redisDao.get(key);
		}
		
	}

}
