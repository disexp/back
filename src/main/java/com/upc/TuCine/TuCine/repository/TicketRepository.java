package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
