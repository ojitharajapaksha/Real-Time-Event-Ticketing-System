package com.realtime.ticketing.system.backend.controller;

import com.realtime.ticketing.system.backend.model.TicketConfiguration;
import com.realtime.ticketing.system.backend.model.TicketLog;
import com.realtime.ticketing.system.backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/configure")
    public String configureSystem(@RequestBody TicketConfiguration config) {
        ticketService.configureSystem(config);
        return "System configured successfully.";
    }

    @PostMapping("/start")
    public String startSystem() {
        ticketService.startSystem();
        return "System started.";
    }

    @PostMapping("/stop")
    public String stopSystem() {
        ticketService.stopSystem();
        return "System stopped.";
    }

    @GetMapping("/logs")
    public List<TicketLog> getLogs() {
        return ticketService.getLogs();
    }

    @GetMapping("/ticket-count")
    public int getTicketCount() {
        return ticketService.getTicketCount();
    }

    @DeleteMapping("/logs")
    public String deleteLogs() {
        ticketService.deleteAllLogs();
        return "All logs have been deleted successfully.";
    }
}
