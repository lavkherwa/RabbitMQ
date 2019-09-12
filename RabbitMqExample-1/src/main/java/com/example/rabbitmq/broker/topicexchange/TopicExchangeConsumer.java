package com.example.rabbitmq.broker.topicexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.broker.defaultexchange.DirectExchangePublisher;
import com.example.rabbitmq.config.RabbitMqConnectionFactory;

@Component
public class TopicExchangeConsumer {

	private final RabbitMqConnectionFactory rabbitMqConnectionFactory;

	Logger LOGGER = LoggerFactory.getLogger(DirectExchangePublisher.class);

	public TopicExchangeConsumer(RabbitMqConnectionFactory rabbitMqConnectionFactory) {
		this.rabbitMqConnectionFactory = rabbitMqConnectionFactory;
	}
}
