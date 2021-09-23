package com.ea.group6.appointmentsystem;

import com.ea.group6.appointmentsystem.service.reservation.ReservationService;
import com.ea.group6.appointmentsystem.service.reservation.ReservationServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class AppointmentSystemApplication {

    public static void main(String[] args) {
                SpringApplication.run(AppointmentSystemApplication.class, args);
        }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
