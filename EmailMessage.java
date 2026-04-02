package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmailMessage {

@Id
private String id;

private String sender;
private String subject;
private String body;

public EmailMessage(){}

public EmailMessage(String id,String sender,String subject,String body){
this.id=id;
this.sender=sender;
this.subject=subject;
this.body=body;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getSender() {
return sender;
}

public void setSender(String sender) {
this.sender = sender;
}

public String getSubject() {
return subject;
}

public void setSubject(String subject) {
this.subject = subject;
}

public String getBody() {
return body;
}

public void setBody(String body) {
this.body = body;
}
}