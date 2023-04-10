package com.cn.liu.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;


/**
 * @author liu
 */
@Configuration
public class ActicveMqConfig {


	/**
	 * mq连接对象
	 */
	@Autowired
	private ConnectionFactory connectionFactory;
	/**
	 * 初始化队列配置
	 *
	 * @return
	 */
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("sms.queue");
	}

	/**
	 * 初始化主题配置
	 *
	 * @return
	 */
	@Bean
	public Topic topic() {
		return new ActiveMQTopic("sms.topic");
	}

	/**
	 * 主题消息容器配置
	 *
	 * @return
	 */
	@Bean
	public JmsListenerContainerFactory<?> topicListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setPubSubDomain(true);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}

	/**
	 * 队列消息容器配置
	 *
	 * @return
	 */
	@Bean
	public DefaultJmsListenerContainerFactory queueListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// 设置为queue方式目标
		factory.setPubSubDomain(false);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}
}
