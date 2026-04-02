package com.example.demo.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.config.RabbitConfig;
import com.example.demo.model.EmailMessage;
import com.example.demo.service.WhatsAppService;
import com.twilio.exception.ApiException;

@Service
public class RabbitMQConsumer {

    private final WhatsAppService whatsAppService;

    public RabbitMQConsumer(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @RabbitListener(queues = RabbitConfig.EMAIL_QUEUE)
    public void receive(EmailMessage email) {

        System.out.println("Message received from queue: " + email.getSubject());

        try {

            // send WhatsApp notification
            whatsAppService.sendWhatsAppNotification(email);

            System.out.println("WhatsApp notification sent successfully");

        } catch (ApiException e) {

            // Twilio daily limit reached
            if (e.getMessage().contains("50 daily messages limit")) {
                System.out.println("Twilio daily limit reached. Skipping this message.");
            } else {
                System.out.println("Twilio API error: " + e.getMessage());
            }

        } catch (Exception e) {

            System.out.println("Unexpected error: " + e.getMessage());

        }
    }
}