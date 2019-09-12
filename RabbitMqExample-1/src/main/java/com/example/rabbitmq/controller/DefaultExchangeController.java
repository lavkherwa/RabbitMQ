package com.example.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.broker.defaultexchange.DirectExchangeConsumer;
import com.example.rabbitmq.broker.defaultexchange.DirectExchangePublisher;

@RestController
@RequestMapping("/defaultExchange")
public class DefaultExchangeController {

	private final DirectExchangePublisher publisher;
	private final DirectExchangeConsumer consumer;

	public DefaultExchangeController(DirectExchangePublisher publisher, DirectExchangeConsumer consumer) {
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
