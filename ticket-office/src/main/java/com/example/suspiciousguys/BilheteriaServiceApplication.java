package com.example.suspiciousguys;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class BilheteriaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BilheteriaServiceApplication.class, args);
    }
}
