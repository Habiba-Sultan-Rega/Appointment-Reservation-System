package com.ea.group6.EmailMicroService.EmailService;

import com.mashape.unirest.http.exceptions.UnirestException;

import javax.jms.JMSException;
import java.util.Map;

public interface EmailReciever {
    public void receiveMessage(Map map) throws InterruptedException, JMSException, UnirestException;
}
