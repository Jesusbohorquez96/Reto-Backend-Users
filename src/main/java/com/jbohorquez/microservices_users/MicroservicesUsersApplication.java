package com.jbohorquez.microservices_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.jbohorquez.microservices_users.infrastructure.adapters.feign.client")
public class MicroservicesUsersApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroservicesUsersApplication.class, args);
	}
}
