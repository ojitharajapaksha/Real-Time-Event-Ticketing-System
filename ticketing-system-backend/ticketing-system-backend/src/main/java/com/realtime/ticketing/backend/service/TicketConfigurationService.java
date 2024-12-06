package com.realtime.ticketing.backend.service;

import com.realtime.ticketing.backend.model.TicketConfiguration;
import com.realtime.ticketing.backend.repository.TicketConfigurationRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketConfigurationService {
    @Autowired
    private TicketConfigurationRepository repository;

    public TicketConfiguration saveConfiguration(TicketConfiguration config) throws IllegalAccessException {
        if (config.getMaxTicketCapacity() < config.getTotalTickets()){
            throw new IllegalAccessException("Maximum ticket capacity cannot be less than total number of tickets.");
        }
        return repository.save(config);
    }

    public Optional<TicketConfiguration> getConfiguration(Log id){
        return repository.findById(id);
    }
}
