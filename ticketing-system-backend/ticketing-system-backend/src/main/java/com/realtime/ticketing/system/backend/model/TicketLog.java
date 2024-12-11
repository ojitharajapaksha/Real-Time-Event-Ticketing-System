package com.realtime.ticketing.system.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class TicketLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String type;
    private String action;

    public TicketLog() {
    }

    public TicketLog(String message, Date timestamp, String type, String action) {
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
        this.action = action;
    }
}
