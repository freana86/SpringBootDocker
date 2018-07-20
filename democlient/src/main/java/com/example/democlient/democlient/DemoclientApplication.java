package com.example.democlient.democlient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoclientApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoclientApplication.class, args);
    }
}
