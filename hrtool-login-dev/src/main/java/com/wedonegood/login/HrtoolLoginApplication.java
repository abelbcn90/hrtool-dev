package com.wedonegood.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.wedonegood")
@EnableJpaRepositories(basePackages = "com.wedonegood")
@EntityScan(basePackages = "com.wedonegood")
public class HrtoolLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrtoolLoginApplication.class, args);
	}
}
