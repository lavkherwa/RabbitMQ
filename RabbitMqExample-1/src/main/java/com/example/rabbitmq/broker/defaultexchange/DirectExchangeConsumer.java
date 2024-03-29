package com.example.rabbitmq.broker.defaultexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.config.RabbitMqConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

@Component
public class DirectExchangeConsumer {

	private final RabbitMqConnectionFactory rabbitMqConnectionFactory;
	Logger LOGGER = LoggerFactory.getLogger(DirectExchangePublisher.class);

	private final static String TASK_QUEUE_NAME = "TestQueue";
	
	public DirectExchangeConsumer(RabbitMqConnectionFactory rabbitMqConnectionFactory) {
		this.rabbitMqConnectionFactory = rabbitMqConnectionFactory;
	}

	public void consumeMessage() throws Exception {

		Connection connection = rabbitMqConnectionFactory.getConnectionFactory().newConnection();
		Channel channel = connection.createChannel();


		channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
		channel.basicQos(1);

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			try {
				// Do your work with the message
				LOGGER.info("Message " + message + " is consumed");
			} finally {
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}
		};

		boolean autoAck = false;
		channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
		});

	}

}
