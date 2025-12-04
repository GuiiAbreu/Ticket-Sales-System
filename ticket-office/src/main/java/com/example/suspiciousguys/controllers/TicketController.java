package com.example.suspiciousguys.controllers;


import com.example.suspiciousguys.dto.TicketUuidDTO;
import com.example.suspiciousguys.models.Ticket;
import com.example.suspiciousguys.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController( TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Ticket>> findAll() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/check/")
    public ResponseEntity<String> findById(@RequestBody TicketUuidDTO dto) {
        var ticket = this.ticketService.findById(dto.uuid());
        if(ticket == null || ticket.getEmail() == null || !ticket.getEmail().equals(dto.email())) {
            return ResponseEntity.status(400).body("Ticket not valid");
        }
        if(ticket.getValitated()){
            return ResponseEntity.status(400).body("Ticket is already valitated");
        }
        return ResponseEntity.ok("Ticket is valid");
    }

    @PatchMapping("/validate/")
    public ResponseEntity<String> validate(@RequestBody TicketUuidDTO dto) {
        var ticket = this.ticketService.findById(dto.uuid());
        if(ticket == null || ticket.getEmail() == null || !ticket.getEmail().equals(dto.email())) {
            return ResponseEntity.status(400).body("Ticket not valid");
        }
        ticketService.confirmTicket(ticket);
        return ResponseEntity.ok("Ticket validated now");
    }






}
