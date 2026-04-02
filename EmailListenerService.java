package com.example.demo.service;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmailMessage;
import com.example.demo.queue.RabbitMQProducer;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.Flags;
import jakarta.mail.search.FlagTerm;

@Service
public class EmailListenerService {

    @Autowired
    private RabbitMQProducer producer;

    // store processed email subjects to avoid duplicates
    private Set<String> processedEmails = new HashSet<>();

    @Scheduled(fixedDelay = 10000) // checks inbox every 10 seconds
    public void checkInbox() {

        try {

            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props);

            Store store = session.getStore("imaps");

            store.connect(
                    "imap.gmail.com",
                    "vaishnavimuthyala02@gmail.com",
                    "yldr qqdr hdzc ohrd"
            );

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (Message msg : messages) {

                String subject = msg.getSubject();

                // prevent duplicate processing
                if (processedEmails.contains(subject)) {
                    continue;
                }

                EmailMessage email = new EmailMessage();
                email.setSender(msg.getFrom()[0].toString());
                email.setSubject(subject);
                email.setBody("New email received");

                producer.sendToQueue(email);

                System.out.println("Email pushed to queue: " + subject);

                // mark as processed
                processedEmails.add(subject);

                // mark email as read
                msg.setFlag(Flags.Flag.SEEN, true);
            }

            inbox.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}