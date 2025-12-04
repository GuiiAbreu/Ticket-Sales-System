package com.suspiciousguys.ticketsystem.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suspiciousguys.ticketsystem.dtos.EmailDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailRequestProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

        public void integrate(EmailDTO emailDTO) {
            try {
                amqpTemplate.convertAndSend(
                        "exchange-email",
                        "routingkey-email",
                        mapper.writeValueAsString(emailDTO)
                );} catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


