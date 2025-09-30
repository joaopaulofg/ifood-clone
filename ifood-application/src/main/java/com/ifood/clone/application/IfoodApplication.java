package com.ifood.clone.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ifood.clone")
public class IfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfoodApplication.class, args);
	}

}
