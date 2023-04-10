package com.cn.liu.util.mq;

import com.cn.liu.entity.User;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author liu
 */
@Component
public class Producer {
	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Resource
	private Queue queue;
	@Resource
	private Topic topic;

	public void sendMsg(User user) {
		System.out.println("发送消息内容 :" + user);
		this.jmsMessagingTemplate.convertAndSend(this.queue,user);
	}

	public void sendTopicMsg(User user) {
		System.out.println("发送消息内容 :" + user);
		this.jmsMessagingTemplate.convertAndSend(this.topic,user);
	}
}
