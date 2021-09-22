package com.ea.group6.EmailMicroService.EmailService;

import java.util.Map;

public interface EmailService {
    public void sendMessage(final String queueName, Map email);
}
