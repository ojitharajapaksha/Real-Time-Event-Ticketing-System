package com.realtime.ticketing.system.backend.service;

import com.realtime.ticketing.system.backend.model.TicketConfiguration;
import com.realtime.ticketing.system.backend.model.TicketLog;
import com.realtime.ticketing.system.backend.model.TicketPool;
import com.realtime.ticketing.system.backend.repository.TicketLogRepository;
import com.realtime.ticketing.system.backend.repository.TicketPoolRepository;
import com.realtime.ticketing.system.backend.threads.TicketCustomer;
import com.realtime.ticketing.system.backend.threads.TicketVendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
public class TicketService {

    @Autowired
    private TicketLogRepository logRepository;

    @Autowired
    private TicketPoolRepository ticketPoolRepository;

    private TicketConfiguration configuration;
    private final BlockingQueue<String> ticketQueue;
    private final ExecutorService = new LinkedBlockingQueue<>();
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
            throw new IllegalAccessException("Error: System is already running.")
        }
    }

}

