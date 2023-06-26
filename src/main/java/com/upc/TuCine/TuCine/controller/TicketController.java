package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Ticket;
import com.upc.TuCine.TuCine.repository.CustomerRepository;
import com.upc.TuCine.TuCine.repository.ShowtimeRepository;
import com.upc.TuCine.TuCine.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public TicketController(
            TicketRepository ticketRepository,
            CustomerRepository customerRepository,
            ShowtimeRepository showtimeRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.showtimeRepository = showtimeRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/tickets
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<List<Ticket>>(ticketRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/tickets
    //Method: POST
    @Transactional
    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        validateTicket(ticket);
        existsCustomerById(ticket.getCustomer_id().getId());
        existsShowtimeById(ticket.getShowtime_id().getId());
        return new ResponseEntity<Ticket>(ticketRepository.save(ticket), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/TuCine/v1/tickets/1
    //Method: PUT
    @Transactional
    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Integer id, @RequestBody Ticket ticket){
        Ticket ticketDB = ticketRepository.findById(id).orElse(null);
        if (ticketDB == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketDB.setCustomer_id(ticket.getCustomer_id());
        ticketDB.setShowtime_id(ticket.getShowtime_id());
        ticketDB.setNumberSeats(ticket.getNumberSeats());
        ticketDB.setTotalPrice(ticket.getTotalPrice());
        validateTicket(ticketDB);
        existsCustomerById(ticketDB.getCustomer_id().getId());
        existsShowtimeById(ticketDB.getShowtime_id().getId());
        return new ResponseEntity<Ticket>(ticketRepository.save(ticketDB), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/tickets/1
    //Method: DELETE
    @Transactional
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable Integer id){
        Ticket ticketDB = ticketRepository.findById(id).orElse(null);
        if (ticketDB == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketRepository.delete(ticketDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateTicket(Ticket ticket) {
        if (ticket.getCustomer_id() == null) {
            throw new RuntimeException("Customer id is required");
        }
        if (ticket.getShowtime_id() == null) {
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
