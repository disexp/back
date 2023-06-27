package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> getAllTickets();

    TicketDto createTicket(TicketDto ticketDto);

    TicketDto updateTicket(Integer id, TicketDto ticketDto);

    TicketDto deleteTicket(Integer id);



}
