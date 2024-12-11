package com.realtime.ticketing.system.backend.service;

import com.realtime.ticketing.system.backend.model.TicketConfiguration;
import com.realtime.ticketing.system.backend.model.TicketLog;
import com.realtime.ticketing.system.backend.model.TicketPool;
import com.realtime.ticketing.system.backend.repository.TicketLogRepository;
import com.realtime.ticketing.system.backend.repository.TicketPoolRepository;
import com.realtime.ticketing.system.backend.threads.TicketCustomer;
import com.realtime.ticketing.system.backend.threads.TicketVendor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
@Service
public class TicketService {

    @Autowired
    private TicketLogRepository logRepository;

    @Autowired
    private TicketPoolRepository ticketPoolRepository;

    private TicketConfiguration configuration;
    private final BlockingQueue<String> ticketQueue;
    private final ExecutorService executorService;
    private boolean isRunning;

    public TicketService(){
        this.isRunning = false;
        this.ticketQueue = new LinkedBlockingQueue<>();
        this.executorService = Executors.newCachedThreadPool();
    }

    public void configureSystem(TicketConfiguration config){
        validateConfiguration(config);
        this.configuration = config;
        logEvent("System configured: " + config.toString(), "INFO", "configure system");
    }

    public void startSystem(){
        if (isRunning){
            throw new IllegalStateException("Error: System is already running.")
        }
        if (configuration == null){
            throw new IllegalStateException("Error: System is not configured. Configure before starting.");
        }
        isRunning=true;
        logEvent("System started.", "INFO", "start system");

        TicketVendor vendor = new TicketVendor(this, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
        TicketCustomer customer = new TicketCustomer(this, configuration.getCustomerRetrievalRate());
        executorService.submit(vendor);
        executorService.submit(customer);
    }

    public void stopSystem(){
        if (!isRunning){
            throw new IllegalStateException("Error: System is not running.");
        }
        isRunning=false;
        executorService.shutdownNow();
        logEvent("System stopped.". "INFO", "stop system");
    }

    public void addTicket(String ticket){
        try{
            if (ticketQueue.size() >= configuration.getMaxTicketCapacity()){
                logEvent("Ticket pool capacity reached. Ticket rejected: " + ticket, "WARNING", "add ticket");
                return;
            }
            ticketQueue.put(ticket);
            ticketPoolRepository.save(new TicketPool(ticket));
            logEvent("Ticket added: " + ticket, "INFO", "add_ticket");
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            logEvent("Error adding ticket: " + e.getMessage(), "ERROR", "add ticket");
        }
    }

    public String removeTicket(){
        String ticket = ticketQueue.poll();
        if (ticket != null){
            ticketPoolRepository.deleteByTicketName(ticket);
            logEvent("Ticket added: " + ticket, "INFO", "add_ticket");
        }catch (InterruptedException e){
            Thread.currentThread()interrupt();
            logEvent("Error adding ticket: " + e.getMessage(), "ERROR", "add ticket");
        }
        return ticket;
    }

    public int getTicketCount(){
        return ticketQueue.size();
    }

    public List<TicketLog> getLogs(){
        return logRepository.findAll();
    }

    public void deleteAllLogs(){
        try{
            logRepository.deleteAll();
            logEvent("All logs have been deleted.", "INFO", "delete logs");
        }catch (Exception e){
            logEvent("Error deleting logs: " + e.getMessage(), "ERROR", "delete logs");
            throw new RuntimeException("Error deleting logs: " + e.getMessage());
        }
    }

    private void logEvent(String message, String type, String action){
        logRepository.save(new TicketLog(message, new Date(), type, action))
    }

    private void validateConfiguration(TicketConfiguration config){
        if (config.getTotalTickets() <= 0 || config.getMaxTicketCapacity() <= 0){
            throw new IllegalArgumentException("All configuration inputs must be positive integers.");
        }
        if (config.getMaxTicketCapacity() < config.getTotalTickets()){
            throw new IllegalArgumentException("Maximum ticket capacity cannot be less than total number of tickets.");
        }
    }
}

