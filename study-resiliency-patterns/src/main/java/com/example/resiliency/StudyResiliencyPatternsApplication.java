package com.example.resiliency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudyResiliencyPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyResiliencyPatternsApplication.class, args);
	}

}
