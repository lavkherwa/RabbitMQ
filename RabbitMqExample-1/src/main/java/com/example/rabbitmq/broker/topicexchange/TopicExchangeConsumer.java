package com.example.rabbitmq.broker.topicexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.broker.defaultexchange.DirectExchangePublisher;
import com.example.rabbitmq.config.RabbitMqConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

@Component
public class TopicExchangeConsumer {

	private final RabbitMqConnectionFactory rabbitMqConnectionFactory;
	Logger LOGGER = LoggerFactory.getLogger(DirectExchangePublisher.class);

	private final static String EXCHANGE_NAME = "topic_logs";

	private final static String QUEUE_NAME = "TopicQueue";

	private final static String QUEUE_BINDING_KEY = "topic";

	public TopicExchangeConsumer(RabbitMqConnectionFactory rabbitMqConnectionFactory) {
		this.rabbitMqConnectionFactory = rabbitMqConnectionFactory;
	}

	public void consumeMessage() throws Exception {

		Connection connection = rabbitMqConnectionFactory.getConnectionFactory().newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, QUEUE_BINDING_KEY);

		channel.queueDeclare(QUEUE_NAME, true, false, false, null);

		/*- Per consumer limit for picking unAck messages from queue at once 
		 * 
		 *  This is useful when you have to do some CPU intensive operation with
		 *  the received message and it will require heavy resources and only few 
		 *  messages can be served by the underline service at once.
		 * 
		 *  In below case we are only picking one message from the Queue to be
		 *  processed at once
		 */
		channel.basicQos(1, false);
		// channel.basicQos(10, true); // Per channel limit

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
		channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
		});

	}

}
