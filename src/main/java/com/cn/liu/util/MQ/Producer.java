package com.cn.liu.util.MQ;

import com.cn.liu.entity.User;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

@Component
public class Producer {
	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Resource
	private Queue queue;

	public void sendMsg(User msg) {
		System.out.println("发送消息内容 :" + msg);
		this.jmsMessagingTemplate.convertAndSend(this.queue,msg);
	}}
