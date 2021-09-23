package com.ea.group6.EmailMicroService.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailServiceImpl {

    @SuppressWarnings("all")
    @Autowired
    private JmsTemplate jmsTemplate;


    public void sendMessage(final String queueName, Map email) {

        System.out.println("Sending Email " + email + "to queue - " + queueName);

        jmsTemplate.convertAndSend(queueName, email);
    }
}
