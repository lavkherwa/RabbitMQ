package com.example.rabbitmq.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqConnection implements AutoCloseable {

	private ConnectionFactory rabbitMqConnectionFactory;
	private Connection connection;

	public RabbitMqConnection(ConnectionFactory rabbitMqConnectionFactory) throws IOException, TimeoutException {
		this.rabbitMqConnectionFactory = rabbitMqConnectionFactory;
		this.connection = this.rabbitMqConnectionFactory.newConnection();
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public void close() throws Exception {
		connection.close();

	}

}
