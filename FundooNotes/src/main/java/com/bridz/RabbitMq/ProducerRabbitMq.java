package com.bridz.RabbitMq;

import org.springframework.core.env.Environment;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProducerRabbitMq {

	@Autowired
	Environment environment;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void publish(String message) {
		rabbitTemplate.convertAndSend(environment.getProperty("rabbitmq.exchangeName"),
				environment.getProperty("rabbitmq.routingKey"), message);
	}

	@Bean
	public Queue queue() {

		return new Queue(environment.getProperty("consumer.queueName"), false);
	}

	@Bean
	public TopicExchange exchange() {

		return new TopicExchange(environment.getProperty("rabbitmq.exchangeName"));
	}

	@Bean
	public Binding binding() {

		return BindingBuilder.bind(queue()).to(exchange()).with(environment.getProperty("rabbitmq.routingKey"));
	}
}
