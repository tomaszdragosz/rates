package com.hsbc.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HsbcTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsbcTestApplication.class, args);
	}

}
