package com.suspiciousguys.ticketsystem.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suspiciousguys.ticketsystem.dtos.IngressoDTO;
import com.suspiciousguys.ticketsystem.dtos.IngressoProducerDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngressoRequestProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    public void integrate(IngressoProducerDTO ingressoDTO) {
        try {
        amqpTemplate.convertAndSend(
                "exchange-ticket",
                "routingkey-ticket",
                mapper.writeValueAsString(ingressoDTO)
        ); } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
