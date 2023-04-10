package com.micro.housekeepingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HousekeepingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousekeepingServiceApplication.class, args);
	}

}
