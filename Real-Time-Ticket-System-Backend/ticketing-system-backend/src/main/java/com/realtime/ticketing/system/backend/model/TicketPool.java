// This class represents the tickets in the pool
package com.realtime.ticketing.system.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TicketPool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Unique identifier for the ticket
    private Long id;
    // Name of the ticket
    private String ticketName;

    public TicketPool() {
    }

    public TicketPool(String ticketName) {
        this.ticketName = ticketName;
    }

    // Getters and Setters for the Ticket pool
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }
}
