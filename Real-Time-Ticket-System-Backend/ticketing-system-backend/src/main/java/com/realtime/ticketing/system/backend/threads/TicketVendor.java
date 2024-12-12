package com.realtime.ticketing.system.backend.threads;

import com.realtime.ticketing.system.backend.service.TicketService;

// This vendor class represent thread that release tickets to the pool
public class TicketVendor implements Runnable {
    private final TicketService ticketService;
    private final int ticketsToGenerate;
    private final int releaseRate;

    public TicketVendor(TicketService ticketService, int ticketsToGenerate, int releaseRate) {
        // Ticket service for manging the tickets
        this.ticketService = ticketService;
        // Total number of tickets to generate
        this.ticketsToGenerate = ticketsToGenerate;
        // Ticket release rate
        this.releaseRate = releaseRate;
    }

    // Run method is executed when the thread started
    @Override
    public void run() {
        for (int i = 1; i <= ticketsToGenerate; i++) {
            String ticket = "Ticket-" + i;
            ticketService.addTicket(ticket);
            System.out.println("Ticket added: " + ticket);
        }
    }
}



