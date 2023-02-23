package com.cn.liu.util.mq;

import com.cn.liu.entity.User;

import com.cn.liu.mapper.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 * @author liu
 */
@Component
public class Consumer {
	@Resource
	UserMapper userMapper;

	@Resource
	RedisTemplate redisTemplate;
	@JmsListener(destination = "sms.queue")
	public void receiveMsg(Message message) throws JMSException {
		System.out.println("接收到消息 : "+message);
		User user = (User)((ObjectMessage)message).getObject();
		userMapper.resgity(user);
		redisTemplate.opsForSet().add("pre"+user.getCardNo(),user.getId());
	}
}
