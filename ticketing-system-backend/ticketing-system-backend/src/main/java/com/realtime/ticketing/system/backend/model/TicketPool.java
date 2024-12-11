package com.realtime.ticketing.system.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TicketPool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticketName;

    public TicketPool(){}

    public TicketPool(String ticketName){
        this.ticketName = ticketName;
    }

    public Long getId(){
        return id;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }
}
