package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.EmailMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class WhatsAppService {

    public static final String ACCOUNT_SID = "AC7317d47370dffe0018ae8b51873bb56b";
    public static final String AUTH_TOKEN = "2e4b935e0746aa3711b5a19cb584a2f3";

    public void sendWhatsAppNotification(EmailMessage email) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String text = "📩 New Email Received\n"
                + "From: " + email.getSender()
                + "\nSubject: " + email.getSubject();

        Message.creator(
                new PhoneNumber("whatsapp:+917671065655"), // your WhatsApp number
                new PhoneNumber("whatsapp:+14155238886"), // Twilio sandbox number
                text
        ).create();
    }
    
}