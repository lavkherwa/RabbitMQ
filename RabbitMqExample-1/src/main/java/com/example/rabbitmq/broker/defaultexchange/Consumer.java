package com.example.rabbitmq.broker.defaultexchange;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.config.RabbitMqChannel;
import com.example.rabbitmq.config.RabbitMqConnection;
import com.example.rabbitmq.config.RabbitMqConnectionFactory;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Component
public class Consumer {

	private final RabbitMqConnectionFactory rabbitMqConnectionFactory;

	Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

	public Consumer(RabbitMqConnectionFactory rabbitMqConnectionFactory) {
		this.rabbitMqConnectionFactory = rabbitMqConnectionFactory;
	}

	public void consumeMessage() throws Exception {
		/* Note: connection and channel classes are auto closable */
		try (RabbitMqConnection connection = new RabbitMqConnection(rabbitMqConnectionFactory.getConnectionFactory())) {
			try (RabbitMqChannel channel = new RabbitMqChannel(connection.getConnection())) {

				String queueName = "TestQueue";
				channel.getChannel().queueDeclare(queueName, true, false, false, null);

				com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel.getChannel()) {
					@Override
					public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
							byte[] body) throws IOException {
						String message = new String(body, "UTF-8");
						System.out.println("Message " + message + " recieved from queue " + queueName);
					}
				};

				channel.getChannel().basicConsume(queueName, false, consumer);

			}
		}
	}

}
