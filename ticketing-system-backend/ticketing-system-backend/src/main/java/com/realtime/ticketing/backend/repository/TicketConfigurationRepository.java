package com.realtime.ticketing.backend.repository;

import com.realtime.ticketing.backend.model.TicketConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketConfigurationRepository extends JpaRepository <TicketConfiguration, Long> {

}
