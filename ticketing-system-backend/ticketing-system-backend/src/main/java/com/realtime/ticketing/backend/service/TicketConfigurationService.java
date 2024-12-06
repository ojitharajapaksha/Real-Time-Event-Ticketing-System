package com.realtime.ticketing.backend.service;

import com.realtime.ticketing.backend.model.TicketConfiguration;
import com.realtime.ticketing.backend.repository.TicketConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketConfigurationService {
    @Autowired
    private TicketConfigurationRepository repository;

    public TicketConfiguration saveConfiguration(TicketConfiguration config){
        return repository.save(config);
    }

    public Optional<TicketConfiguration> getConfiguration(Long id){
        return repository.findById(id);
    }
}
