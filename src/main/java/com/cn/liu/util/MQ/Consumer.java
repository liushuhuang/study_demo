package com.cn.liu.util.MQ;

import com.cn.liu.entity.User;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Component
public class Consumer {
	@JmsListener(destination = "sms.queue")
	public void receiveMsg(User text){
		System.out.println("接收到消息 : "+text);
	}
}
