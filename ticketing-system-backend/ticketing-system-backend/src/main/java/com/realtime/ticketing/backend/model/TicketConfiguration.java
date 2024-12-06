package com.realtime.ticketing.backend.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TicketConfiguration {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalTickets;
    private int ticketRetrievalRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Long getId() {
        return id;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketRetrievalRate() {
        return ticketRetrievalRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setTicketRetrievalRate(int ticketRetrievalRate) {
        this.ticketRetrievalRate = ticketRetrievalRate;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }
}
