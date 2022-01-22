package com.example.hrinterface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("123@gmail.com");//this should be the same as spring.mail.username in application.properties;
        message.setTo(toEmail);
        message.setText("click this link to register: "+body);
        message.setSubject(subject);
        mailSender.send(message);
    }
}
