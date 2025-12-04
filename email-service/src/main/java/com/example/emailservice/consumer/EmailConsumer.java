package com.example.emailservice.consumer;

import com.example.emailservice.dtos.EmaIlDTO;
import com.example.emailservice.services.EmailService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final Gson gson = new Gson();
    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = {"queue-email"})
    public void receiveMessage(String dto) {
        try {
            EmaIlDTO emailDTO = gson.fromJson(dto, EmaIlDTO.class);
            emailService.sendEmail(emailDTO.email(), "Compra de Ticket", emailDTO.message());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
