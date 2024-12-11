// Repository for the ticket log
package com.realtime.ticketing.system.backend.repository;

import com.realtime.ticketing.system.backend.model.TicketLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketLogRepository extends JpaRepository<TicketLog, Long> {
}
