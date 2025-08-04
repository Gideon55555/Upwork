package com.upwork.movercrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoverCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoverCrmApplication.class, args);
    }
} 