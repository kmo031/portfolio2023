package com.sangmin.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Portfolio1Application {

	public static void main(String[] args) {
		SpringApplication.run(Portfolio1Application.class, args);
	}

}
