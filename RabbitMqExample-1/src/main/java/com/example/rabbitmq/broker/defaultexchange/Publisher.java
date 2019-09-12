package com.example.rabbitmq.broker.defaultexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.config.RabbitMqChannel;
import com.example.rabbitmq.config.RabbitMqConnection;
import com.example.rabbitmq.config.RabbitMqConnectionFactory;

/*- Default exchange is the Direct exchange
 *  Queue name becomes the binding key
 * 
 * 
 */

@Component
public class Publisher {

	private final RabbitMqConnectionFactory rabbitMqConnectionFactory;

	Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

	public Publisher(RabbitMqConnectionFactory rabbitMqConnectionFactory) {
		this.rabbitMqConnectionFactory = rabbitMqConnectionFactory;
	}

	public void publishMessage() throws Exception {
		/* Note: connection and channel classes are auto closable */
		try (RabbitMqConnection connection = new RabbitMqConnection(rabbitMqConnectionFactory.getConnectionFactory())) {
			try (RabbitMqChannel channel = new RabbitMqChannel(connection.getConnection())) {
				String queueName = "TestQueue";
				for (int i = 0; i < 10; i++) {
					String message = "This is message: #" + (i + 1);
					/* Publish all the messages into the Queue */
					channel//
							.getChannel()//
							.basicPublish("", queueName, null, message.getBytes());
					LOGGER.info("Message " + message + " sent to queue " + queueName);
				}
			}
		}
	}

}
