package com.example.suspiciousguys.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ticketId;
    private String email;
    private Boolean valitated;

    public Ticket() {

    }

    public Ticket (String email, Boolean valitated) {
        this.email = email;
        this.valitated = valitated;
    }

    public Boolean getValitated() {
        return valitated;
    }

    public void setValitated(Boolean valitated) {
        this.valitated = valitated;
    }

    public UUID getTicketId() {
        return ticketId;
    }

//    //public void setTicketId(UUID ticketId) {
//        this.ticketId = ticketId;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
