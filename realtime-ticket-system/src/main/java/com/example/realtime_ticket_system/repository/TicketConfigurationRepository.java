package com.example.realtime_ticket_system.repository;

import com.example.realtime_ticket_system.model.TicketConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketConfigurationRepository extends JpaRepository<TicketConfiguration, Long> {
}