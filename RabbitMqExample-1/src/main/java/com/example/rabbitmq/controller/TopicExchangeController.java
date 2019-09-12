package com.example.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.broker.topicexchange.TopicExchangeConsumer;
import com.example.rabbitmq.broker.topicexchange.TopicExchangePublisher;

@RestController
@RequestMapping("/topicExchange")
public class TopicExchangeController {

	private final TopicExchangePublisher publisher;
	private final TopicExchangeConsumer consumer;

	public TopicExchangeController(TopicExchangePublisher publisher, TopicExchangeConsumer consumer) {
		this.publisher = publisher;
		this.consumer = consumer;

	}

	@GetMapping("/publish")
	public void publish(@RequestParam String bindingKey, @RequestParam String message) throws Exception {
		publisher.publishMessage(bindingKey, message);
	}

//	@GetMapping("/consume")
//	public void consume() throws Exception {
//		consumer.consumeMessage();
//	}

}
