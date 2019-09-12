package com.example.rabbitmq.broker.defaultexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.config.RabbitMqConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

@Component
public class Consumer {

	private final RabbitMqConnectionFactory rabbitMqConnectionFactory;

	Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

	public Consumer(RabbitMqConnectionFactory rabbitMqConnectionFactory) {
		this.rabbitMqConnectionFactory = rabbitMqConnectionFactory;
	}

	public void consumeMessage() throws Exception {

		Connection connection = rabbitMqConnectionFactory.getConnectionFactory().newConnection();
		Channel channel = connection.createChannel();

		String TASK_QUEUE_NAME = "TestQueue";

		channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
		channel.basicQos(1);

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println("Message " + message + " is consumed");
			try {
				// do any work with the message
			} finally {
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}
		};

		boolean autoAck = false;
		channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
		});

	}

}
