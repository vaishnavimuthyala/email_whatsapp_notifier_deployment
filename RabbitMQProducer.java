package com.example.demo.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Component;

import com.example.demo.config.RabbitConfig;
import com.example.demo.model.EmailMessage;


@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToQueue(EmailMessage email) {
        rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_QUEUE, email); // works now
        System.out.println("Sent to queue: " + email.getSubject());
        
    }
}