package com.jo.dy.ot.redis;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.jo.dy.ot.model.DoWork;
import com.jo.dy.ot.redis.dao.RedisDao;
import com.jo.dy.ot.thread.ThreadPool;
import com.jo.dy.ot.util.Constants;

/**
 * 起一个新的线程监听相应的频道的消息,需要异步处理的数据只需实现DoWork接口即可
 * 
 */
@Component
public class RedisListener implements MessageListener {

	private static Logger logger = Logger.getLogger(RedisListener.class);

	@Resource
	private RedisDao redisDao;

	@PostConstruct
	public void init() {
		int len = redisDao.getLinkLength(Constants.REDIS_CHANNEL);
		if (len > 0) {
			redisDao.convertAndSend(Constants.REDIS_CHANNEL, Constants.REDIS_CHANNEL);
		}
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		logger.info("redis监听到.....");
		if (message == null || message.getChannel() == null) {
			return;
		}
		String chnanel = new String(message.getChannel());
		String key = (String) SerializationUtils.deserialize(message.getBody());
		logger.info("redis监听到频道为:" + chnanel + "的信息");
		if (Constants.REDIS_CHANNEL.equals(chnanel)) {
			ThreadPool instance = ThreadPool.getInstance();
			while (true) {
				DoWork object = (DoWork) redisDao.get(key);
				if (object == null) {
					break;
				}
				instance.excute(object);
			}
		}

	}

}
