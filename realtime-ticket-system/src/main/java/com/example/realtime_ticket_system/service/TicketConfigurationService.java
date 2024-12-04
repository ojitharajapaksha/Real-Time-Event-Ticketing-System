package com.example.realtime_ticket_system.service;

import com.example.realtime_ticket_system.model.TicketConfiguration;
import com.example.realtime_ticket_system.repository.TicketConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketConfigurationService {
    @Autowired
    private TicketConfigurationRepository repository;

    public TicketConfiguration saveConfiguration(TicketConfiguration config) {
        return repository.save(config);
    }

    public Optional<TicketConfiguration> getConfiguration(Long id) {
        return repository.findById(id);
    }
}
