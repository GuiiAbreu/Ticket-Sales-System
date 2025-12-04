package com.example.suspiciousguys.consumer;

import com.example.suspiciousguys.dto.TicketRequestConsumerDTO;
import com.example.suspiciousguys.models.Ticket;
import com.example.suspiciousguys.services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Components
public class TicketRequestConsumer {

    private final TicketService ticketService;

    public TicketRequestConsumer(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RabbitListener(queues = {"queue-ticket"})
    public void receive(@Payload String dto) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            TicketRequestConsumerDTO ticketRequestDTO = mapper.readValue(dto, TicketRequestConsumerDTO.class);
            Ticket ticket = new Ticket();

            ticket.setValitated(false);
            ticket.setEmail(ticketRequestDTO.email());
            ticketService.save(ticket);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
