package com.ea.group6.EmailMicroService.EmailService;

import com.ea.group6.EmailMicroService.Domain.Email;
import com.ea.group6.EmailMicroService.EmailRepository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javax.jms.JMSException;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class EmailRecieverImpl {

    @Autowired
    private EmailRepository emailRepository;

    @JmsListener(destination = "inbound.queue")
    public void receiveMessage(Map map) throws InterruptedException, JMSException, UnirestException {

//     HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/sandboxc09a4ad78ea747d6b21635e5d4183b4b.mailgun.org/messages")
//                .basicAuth("api", "66c39f7a08a30f3b7f35ae9557226cc0-45f7aa85-a5ff80c6")
//                .queryString("from", map.get("from"))
//                .queryString("to", map.get("to"))
//                .queryString("subject", map.get("subject"))
//                .queryString("text", map.get("text"))
//                .asJson();


        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/sandbox2b900b89ad194ee0861e6b9f5d5fe609.mailgun.org/messages")
                .basicAuth("api", "66c39f7a08a30f3b7f35ae9557226cc0-45f7aa85-a5ff80c6")
                .queryString("from", "hrega@miu.edu")
                .queryString("to", map.get("to"))
                .queryString("subject", map.get("subject"))
                .queryString("text", map.get("text"))
                .asJson();

        System.out.println( request.getBody());
        emailRepository.save(new Email(map.get("to").toString(), map.get("subject").toString(), map.get("from").toString(), map.get("text").toString(), LocalDateTime.now()));
        System.out.println("sender= " + map.get("from") );
    }
}