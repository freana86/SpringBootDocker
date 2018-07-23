package com.example.jdbc.jdbcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JdbcdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcdemoApplication.class, args);
	}
}
