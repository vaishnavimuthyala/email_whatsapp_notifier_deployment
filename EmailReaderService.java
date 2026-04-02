package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.EmailMessage;
import com.example.demo.queue.RabbitMQProducer;

@Service
public class EmailReaderService {

    private final RabbitMQProducer producer;

    public EmailReaderService(RabbitMQProducer producer) {
        this.producer = producer;
    }

    // This method is called by the controller
    public void processEmail(EmailMessage email) {

        // Send email directly to RabbitMQ
        producer.sendToQueue(email);
    }
}