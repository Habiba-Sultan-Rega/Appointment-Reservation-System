package com.ea.group6.Registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegistryApplication {

	public static  void main(String[] args) {

		SpringApplication.run(RegistryApplication.class, args);
	}

}
