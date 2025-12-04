package com.example.suspiciousguys.services;

import com.example.suspiciousguys.models.Ticket;
import com.example.suspiciousguys.repositories.TicketRepository;
import com.example.suspiciousguys.utils.GenericCRUDService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService implements GenericCRUDService<Ticket> {

    private final TicketRepository repository;
    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }


    public void confirmTicket(Ticket ticket) {
        var ticketFounded = findById(ticket.getTicketId());
        if(ticketFounded == null || ticketFounded.getValitated()) return;
        if(ticket.getEmail().equals(ticketFounded.getEmail())){
            ticket.setValitated(true);
            repository.save(ticket);
        };
    }


    @Override
    public Ticket findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Ticket> findAll() {
        return repository.findAll();
    }


    public Ticket save(Ticket ticket) {
        return repository.save(ticket);
    }

    @Override
    public void delete(Ticket entity) {
        repository.delete(entity);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
