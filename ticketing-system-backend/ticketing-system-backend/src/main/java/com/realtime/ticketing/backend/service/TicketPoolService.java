package com.realtime.ticketing.backend.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class TicketPoolService {
    private final BlockingQueue<String> tickets = new LinkedBlockingQueue<>();
    private final int maxCapacity = 10;

    public void addTicket(String ticket) throws InterruptedException{
        if (ticket.size() >= maxCapacity){
            Thread.sleep(100);
        }
        ticket.put(ticket);
    }
    public String removeTicket(){
        return tickets.poll();
    }
    public int getTicketCount{
        return tickets.size();
    }
}

