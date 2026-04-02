package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.EmailMessage;

public interface EmailRepository extends JpaRepository<EmailMessage,String>{

}