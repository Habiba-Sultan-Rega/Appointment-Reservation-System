package com.ea.group6.EmailMicroService.EmailController;

import com.ea.group6.EmailMicroService.EmailService.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@EnableJms
@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/emails")
    public String SendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text){

        Map<String, String> map = new HashMap<>() ;
        map.put("to", to);
        map.put("from", "rega.habiba@gmail.com");
        map.put("subject", subject);
        map.put("text", text);

        emailService.sendMessage("inbound.queue", map);

        return "Email Sending job has been started Successfully";
    }
}
