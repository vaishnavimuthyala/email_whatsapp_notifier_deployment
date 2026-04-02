package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.EmailMessage;
import com.example.demo.service.EmailReaderService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

private final EmailReaderService service;

public NotificationController(EmailReaderService service){

this.service=service;

}

@PostMapping("/email")
public String receiveEmail(@RequestBody EmailMessage email){

service.processEmail(email);

return "Notification successed";

}

}