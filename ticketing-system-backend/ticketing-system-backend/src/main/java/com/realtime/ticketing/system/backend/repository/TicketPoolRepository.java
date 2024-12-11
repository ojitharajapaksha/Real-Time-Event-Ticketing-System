package com.realtime.ticketing.system.backend.repository;

import com.realtime.ticketing.system.backend.model.TicketPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPoolRepository extends JpaRepository<TicketPool, Long>{
    void deleteByTicketName(String ticketName);
}
