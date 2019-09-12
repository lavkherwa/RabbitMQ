package com.example.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.broker.defaultexchange.Consumer;
import com.example.rabbitmq.broker.defaultexchange.Publisher;

@RestController
@RequestMapping("/defaultExchange")
public class DefaultExchangeController {

	private final Publisher publisher;
	private final Consumer consumer;

	public DefaultExchangeController(Publisher publisher, Consumer consumer) {
		this.publisher = publisher;
		this.consumer = consumer;

	}

	@GetMapping("/publish")
	public void publish() throws Exception {
		publisher.publishMessage();
	}

	@GetMapping("/consume")
	public void consume() throws Exception {
		consumer.consumeMessage();
	}

}
