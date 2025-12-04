package com.example.bilheteriaservice;

import com.example.bilheteriaservice.models.Ticket;
import com.example.bilheteriaservice.services.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BilheteriaServiceApplicationIntegrationTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16");

    @Autowired
    TicketService ticketService;

    @Test
    void contextLoads() {
        assertTrue(postgreSQLContainer.isCreated());
    }

    @Test
    void testTicketCreation() {
        UUID ticketId = UUID.randomUUID();
        String cpf = "12345678901";
        Ticket ticket = new Ticket(ticketId, cpf, false);

        assertEquals(ticketId, ticket.getTicketId());
        assertEquals(cpf, ticket.getCpf());
    }

    @Test
    void testSaveAndFindTicket() {
        UUID ticketId = UUID.randomUUID();
        String cpf = "12345678901";
        Ticket ticket = new Ticket(ticketId, cpf, false);

        ticketService.save(ticket);

        Ticket foundTicket = ticketService.findById(ticketId);
        assertNotNull(foundTicket, "O ticket deve ser encontrado após ser salvo");
        assertEquals(cpf, foundTicket.getCpf(), "O CPF do ticket recuperado deve ser igual ao informado");
    }


}
