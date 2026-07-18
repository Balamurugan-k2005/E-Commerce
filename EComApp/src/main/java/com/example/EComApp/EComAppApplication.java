package com.example.EComApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EComAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EComAppApplication.class, args);
	}

}
