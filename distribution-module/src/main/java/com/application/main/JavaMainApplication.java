package com.application.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.application.*")
public class JavaMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaMainApplication.class, args);
	}

}
