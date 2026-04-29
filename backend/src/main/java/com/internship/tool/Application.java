package com.internship.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync   // ✅ REQUIRED for @Async to work
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}