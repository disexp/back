package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.TicketDto;
import com.upc.TuCine.TuCine.model.Showtime;
import com.upc.TuCine.TuCine.model.Ticket;
import com.upc.TuCine.TuCine.repository.CustomerRepository;
import com.upc.TuCine.TuCine.repository.FilmRepository;
import com.upc.TuCine.TuCine.repository.ShowtimeRepository;
import com.upc.TuCine.TuCine.repository.TicketRepository;
import com.upc.TuCine.TuCine.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TicketServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    private TicketDto EntityToDto(Ticket ticket){
        return modelMapper.map(ticket, TicketDto.class);
    }

    private Ticket DtoToEntity(TicketDto ticketDto){
        return modelMapper.map(ticketDto, Ticket.class);
    }
    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        validateTicket(ticketDto);
        existsCustomerById(ticketDto.getCustomer().getId());
        existsShowtimeById(ticketDto.getShowtime().getId());

        Ticket ticket = DtoToEntity(ticketDto);
        Ticket createdTicket = ticketRepository.save(ticket);
        return EntityToDto(createdTicket);
    }

    @Override
    public TicketDto updateTicket(Integer id, TicketDto ticketDto) {
        Ticket ticketToUpdate = ticketRepository.findById(id).orElse(null);
        if (ticketToUpdate == null) {
            return null; // O lanzar una excepción si lo prefieres
        }
        validateTicket(ticketDto);
        existsCustomerById(ticketDto.getCustomer().getId());
        existsShowtimeById(ticketDto.getShowtime().getId());

        // Actualizar los campos del ticket existente
        ticketToUpdate.setCustomer_id(ticketDto.getCustomer());
        ticketToUpdate.setShowtime_id(ticketDto.getShowtime());
        ticketToUpdate.setNumberSeats(ticketDto.getNumberSeats());
        ticketToUpdate.setTotalPrice(ticketDto.getTotalPrice());

        validateTicket(ticketDto);
        existsCustomerById(ticketDto.getCustomer().getId());
        existsShowtimeById(ticketDto.getShowtime().getId());

        Ticket updatedTicket = ticketRepository.save(ticketToUpdate);
        return EntityToDto(updatedTicket);
    }

    @Override
    public TicketDto deleteTicket(Integer id) {
        Ticket ticketToDelete = ticketRepository.findById(id).orElse(null);
        if (ticketToDelete == null) {
            return null; // O lanzar una excepción si lo prefieres
        }
        ticketRepository.delete(ticketToDelete);
        return EntityToDto(ticketToDelete);
    }

    private void validateTicket(TicketDto ticket) {
        if (ticket.getCustomer() == null) {
            throw new RuntimeException("Customer id is required");
        }
        if (ticket.getShowtime() == null) {
            throw new RuntimeException("Showtime id is required");
        }
        if (ticket.getNumberSeats() == null || ticket.getNumberSeats() == 0) {
            throw new RuntimeException("Number of seats is required and must be greater than 0");
        }
        if (ticket.getTotalPrice() == null || ticket.getTotalPrice() == 0) {
            throw new RuntimeException("Total price is required and must be greater than 0");
        }
    }

    private void existsCustomerById(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer id not found");
        }
    }

    private void existsShowtimeById(Integer id) {
        if (!showtimeRepository.existsById(id)) {
            throw new RuntimeException("Showtime id not found");
        }
    }
}
