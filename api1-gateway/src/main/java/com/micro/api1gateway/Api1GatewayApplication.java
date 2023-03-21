package com.micro.api1gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Api1GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Api1GatewayApplication.class, args);
	}

}
