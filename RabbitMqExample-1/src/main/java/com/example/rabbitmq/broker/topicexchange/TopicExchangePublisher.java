package com.example.rabbitmq.broker.topicexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.config.RabbitMqConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Component
public class TopicExchangePublisher {

	private final RabbitMqConnectionFactory rabbitMqConnectionFactory;
	Logger LOGGER = LoggerFactory.getLogger(TopicExchangePublisher.class);

	private final static String EXCHANGE_NAME = "topic_logs";

	public TopicExchangePublisher(RabbitMqConnectionFactory rabbitMqConnectionFactory) {
		this.rabbitMqConnectionFactory = rabbitMqConnectionFactory;
	}

	public void publishMessage(String routingKey, String message) throws Exception {
		/* Note: connection and channel classes are auto closable */
		try (Connection connection = rabbitMqConnectionFactory.getConnectionFactory().newConnection()) {
			try (Channel channel = connection.createChannel()) {

				channel.exchangeDeclare(EXCHANGE_NAME, "topic");
				
				channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
				
				LOGGER.info("Message " + message + " is sent via routing key " + routingKey);
			}
		}
	}
}
