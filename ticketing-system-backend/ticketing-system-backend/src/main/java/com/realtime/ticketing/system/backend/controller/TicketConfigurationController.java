package com.realtime.ticketing.system.backend.controller;

import com.realtime.ticketing.system.backend.model.TicketConfiguration;
import com.realtime.ticketing.system.backend.service.TicketService;
import com.realtime.ticketing.system.backend.model.TicketLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketConfigurationController {

    @Autowired
    private TicketService service;
    @Autowired
    private TicketService ticketService;

    @PostMapping("/configure")
    public String configureSystem(@RequestBody TicketConfiguration config) {
        ticketService.configureSystem(config);
        return "System Configuration Successfull.";
    }

    @PostMapping("/start")
    public String startSystem() {
        ticketService.startSystem();
        return "System Started."

    }

    @PostMapping("/stop")
    public String startSystem() {
        ticketService.stopSystem();
        return "System Stopped."
    }

    @GetMapping("/logs")
    public List<TicketLog> getLogs() {
        return ticketService.getLogs();
    }

    @GetMapping("/ticketCount")
    public int getTicketCount() {
        return ticketService.getTicketCount();
    }

    @DeleteMapping("/logs")
    public String deleteLogs() {
        ticketService.deleteAllLogs();
        return "All logs have been deleted successfully.";

    }
}
