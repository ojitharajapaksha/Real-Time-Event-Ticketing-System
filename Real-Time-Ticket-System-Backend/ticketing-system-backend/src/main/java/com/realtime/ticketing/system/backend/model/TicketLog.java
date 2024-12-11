// Ticket logs representing by this class
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
    // Unique identifier for the log
    private Long id;
    // Log message
    private String message;
    // Timestamp of the log
    private Date timestamp;
    // Log type (ex:- INFO, WARNING, ERROR)
    private String type;
    // Action associated with the log
    private String action;

    public TicketLog() {}

    public TicketLog(String message, Date timestamp, String type, String action) {
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
        this.action = action;
    }

    // Getters and Setters for the Ticket log
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
