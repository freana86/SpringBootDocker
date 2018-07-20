package com.example.uttag.demouttag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemouttagApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemouttagApplication.class, args);
	}
}
