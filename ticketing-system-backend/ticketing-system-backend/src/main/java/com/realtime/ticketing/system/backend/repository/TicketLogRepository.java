package com.realtime.ticketing.system.backend.repository;

import com.realtime.ticketing.system.backend.model.TicketConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketLogRepository extends JpaRepository <TicketConfiguration, Long> {

}
