package com.example.rabbitmq.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RabbitMqChannel implements AutoCloseable {

	private Connection connection;
	private Channel channel;

	public RabbitMqChannel(Connection connection) throws IOException, TimeoutException {
		this.connection = connection;
		this.channel = this.connection.createChannel();
	}

	public Channel getChannel() {
		return channel;
	}

	@Override
	public void close() throws Exception {
		channel.close();

	}

}
