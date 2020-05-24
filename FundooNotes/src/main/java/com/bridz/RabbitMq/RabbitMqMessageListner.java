package com.bridz.RabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqMessageListner {

	@Autowired
	Environment environment;

	@RabbitListener(queues = "${consumer.queueName}")
	public void processMessage(String message) {

		System.out.print("Received message : " + message);
	}

}
