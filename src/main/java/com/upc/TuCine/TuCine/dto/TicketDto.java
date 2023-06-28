package com.upc.TuCine.TuCine.dto;

import com.upc.TuCine.TuCine.model.Customer;
import com.upc.TuCine.TuCine.model.Showtime;
import lombok.Data;

@Data
public class TicketDto {
    private Integer id;
    private Integer numberSeats;
    private Float totalPrice;
    private Customer customer;
    private Showtime showtime;
}
