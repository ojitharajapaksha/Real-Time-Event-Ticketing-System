package com.realtime.ticketing.system.backend.threads;

import com.realtime.ticketing.system.backend.service.TicketService;

public class TicketVendor implements Runnable{
    private final TicketService ticketService;
    private final int ticketsToGenarate;
    private final int releaseRate;

    public TicketVendor(TicketService ticketService, int ticketsToGenarate, int releaseRate){
        this.ticketService=ticketService;
        this.ticketsToGenarate=ticketsToGenarate;
        this.releaseRate=releaseRate;
    }

    @Override
    public void run(){
        for(int i=1; i<=ticketsToGenarate; i++){
            String ticket = "Ticket" + i;
            ticketService.addTicket(ticket);
            System.out.println("Ticket added: " + ticket);
        }
    }
}
