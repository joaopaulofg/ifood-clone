package com.joaopaulofg.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IfoodCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfoodCloneApplication.class, args);
	}

}
