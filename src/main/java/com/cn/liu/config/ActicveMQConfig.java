package com.cn.liu.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;


/**
 * @author liu
 */
@Configuration
public class ActicveMQConfig {
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("sms.queue");
	}
}
