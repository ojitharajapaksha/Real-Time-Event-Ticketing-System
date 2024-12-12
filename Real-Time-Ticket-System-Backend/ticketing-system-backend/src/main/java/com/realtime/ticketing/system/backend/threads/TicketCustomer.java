package com.realtime.ticketing.system.backend.threads;

import com.realtime.ticketing.system.backend.service.TicketService;

import java.util.concurrent.TimeUnit;

public class TicketCustomer implements Runnable {

    private final TicketService ticketService;
    private final int retrievalRate;

    // The customer class represents the thread that purchasing tickets
    public TicketCustomer(TicketService ticketService, int retrievalRate) {
        // Ticket service for managing ticket operations
        this.ticketService = ticketService;
        // Ticket retrieved rate
        this.retrievalRate = retrievalRate;
    }

    // Run methods is executed when the thread starts.
    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            String ticket = ticketService.removeTicket();
            if (ticket != null) {

                System.out.println("Ticket purchased: " + ticket);
            } else {

                System.out.println("No tickets available for purchase.");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                    break;
                }
            }


            try {
                TimeUnit.MILLISECONDS.sleep(1000 / retrievalRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("TicketCustomer thread has been stopped.");
    }
}
