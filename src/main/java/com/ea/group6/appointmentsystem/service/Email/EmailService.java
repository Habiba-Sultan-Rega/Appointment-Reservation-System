package com.ea.group6.appointmentsystem.service.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Component
public class EmailService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    @Value("${email-service.service-name}")
    private String emailserviceServerName;


    public void sendEmailFromService(String to, String subject, String text) {
        String url = getBaseServiceUrl() + "/api/emails/?to="+to+"&subject="+subject+"&text="+text;
        restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(), String.class).getBody();

    }

    private HttpEntity<Object> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        return new HttpEntity<>(headers);
    }

    private String getBaseServiceUrl() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(emailserviceServerName);
        serviceInstances.forEach(System.out::println);
        return serviceInstances.get(0).getUri().toString();
    }

}

