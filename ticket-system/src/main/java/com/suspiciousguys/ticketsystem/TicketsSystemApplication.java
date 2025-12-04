package com.suspiciousguys.ticketsystem;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class TicketsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketsSystemApplication.class, args);
        System.out.println("ta rodando");
    }

}
