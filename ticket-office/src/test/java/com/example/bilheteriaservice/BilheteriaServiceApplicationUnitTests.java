package com.example.bilheteriaservice;

import com.example.bilheteriaservice.models.Ticket;
import com.example.bilheteriaservice.repositories.TicketRepository;
import com.example.bilheteriaservice.services.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BilheteriaServiceApplicationUnitTests {


    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testFindAll() {
        Ticket ticket1 = new Ticket(UUID.randomUUID(), "12345678901", false);
        Ticket ticket2 = new Ticket(UUID.randomUUID(), "98765432100", false);
        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.findAll();

        assertEquals(2, result.size(), "Deve retornar 2 tickets");
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        UUID ticketId = UUID.randomUUID();
        Ticket ticket = new Ticket(ticketId, "12345678901", false);

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket savedTicket = ticketService.save(ticket);

        assertNotNull(savedTicket, "O ticket salvo não deve ser nulo");
        assertEquals(ticketId, savedTicket.getTicketId(), "O ticketId deve ser o mesmo");
        verify(ticketRepository, times(1)).save(ticket);
    }


}
