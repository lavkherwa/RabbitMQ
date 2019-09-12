package com.example.rabbitmq.broker.defaultexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.config.RabbitMqConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/*- Default exchange is the Direct exchange
 *  Queue name becomes the binding key
 * 
 * 
 */

@Component
public class DirectExchangePublisher {

	private final RabbitMqConnectionFactory rabbitMqConnectionFactory;

	Logger LOGGER = LoggerFactory.getLogger(DirectExchangePublisher.class);

	public DirectExchangePublisher(RabbitMqConnectionFactory rabbitMqConnectionFactory) {
		this.rabbitMqConnectionFactory = rabbitMqConnectionFactory;
	}

	public void publishMessage() throws Exception {
		/* Note: connection and channel classes are auto closable */
		try (Connection connection = rabbitMqConnectionFactory.getConnectionFactory().newConnection()) {
			try (Channel channel = connection.createChannel()) {
				String TASK_QUEUE_NAME = "TestQueue";
				for (int i = 0; i < 10; i++) {
					String message = "This is message: #" + (i + 1);
					/* Publish all the messages into the Queue */
					channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes());
					LOGGER.info("Message " + message + " sent to queue " + TASK_QUEUE_NAME);
				}
			}
		}
	}

}
