package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.TicketDto;
import com.upc.TuCine.TuCine.service.TicketService;
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
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/tickets
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/tickets
    //Method: POST
    @Transactional
    @PostMapping("/tickets")
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto){
        TicketDto createdTicketDto= ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(createdTicketDto, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/TuCine/v1/tickets/1
    //Method: PUT
    @Transactional
    @PutMapping("/tickets/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Integer id, @RequestBody TicketDto ticketDto){
        TicketDto updatedTicketDto = ticketService.updateTicket(id, ticketDto);
        if (updatedTicketDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTicketDto,HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/tickets/1
    //Method: DELETE
    @Transactional
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<TicketDto> deleteTicket(@PathVariable Integer id){
        TicketDto deletedTicketDto = ticketService.deleteTicket(id);
        if (deletedTicketDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedTicketDto,HttpStatus.OK);
    }


}
