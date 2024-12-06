package com.realtime.ticketing.backend.controller;

import com.realtime.ticketing.backend.model.TicketConfiguration;
import com.realtime.ticketing.backend.service.TicketConfigurationService;
import com.realtime.ticketing.backend.model.TicketConfiguration;
import com.realtime.ticketing.backend.service.TicketConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configurations")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketConfigurationController {

    @Autowired
    private TicketConfigurationService service;

    @PostMapping
    public TicketConfiguration save(@RequestBody TicketConfiguration config) throws IllegalAccessException{
        return service.saveConfiguration(config);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketConfiguration> get(@PathVariable Long id){
        return service.getConfiguration(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
