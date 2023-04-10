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
	@JmsListener(destination = "sms.queue", containerFactory = "queueListenerContainerFactory")
	public void receiveMsg(Message message) throws JMSException {
		//System.out.println("接收到消息 : "+message);
		User user = (User)((ObjectMessage)message).getObject();
		System.out.println("queue接收信息："+user);
	}

	@JmsListener(destination = "sms.topic", containerFactory = "topicListenerContainerFactory")
	public void receiveTopecMsg(Message message) throws JMSException {
		//System.out.println("接收到消息 : "+message);
		User user = (User)((ObjectMessage)message).getObject();
		System.out.println("topic接收信息："+user);
	}

	@JmsListener(destination = "sms.topic", containerFactory = "topicListenerContainerFactory")
	public void receiveTopecMsg1(Message message) throws JMSException {
		//System.out.println("接收到消息 : "+message);
		User user = (User)((ObjectMessage)message).getObject();
		System.out.println("topic1接收信息："+user);
	}
}
