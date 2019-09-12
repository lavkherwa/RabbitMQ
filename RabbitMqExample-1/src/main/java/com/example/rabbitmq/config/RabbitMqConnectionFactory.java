package com.example.rabbitmq.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.ConnectionFactory;

@Component
public class RabbitMqConnectionFactory {

	public ConnectionFactory getConnectionFactory() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		/* Real scenarios these should come from environment variables */
		factory.setHost("localhost");
		factory.setPort(5672); /*- NOTE: port 15672 shows the web UI and actual server port is 5672 */
		factory.setUsername("guest");
		factory.setPassword("guest");
		return factory;
	}

}
