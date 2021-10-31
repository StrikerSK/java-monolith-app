package com.application.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.application.*"})
@EnableJpaRepositories(basePackages = {"com.application.*"})
@SpringBootApplication(scanBasePackages = "com.application.*")
public class JavaMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaMainApplication.class, args);
	}

}
