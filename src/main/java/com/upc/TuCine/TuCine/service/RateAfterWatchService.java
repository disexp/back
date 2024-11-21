package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.model.ContentRating;
import com.upc.TuCine.TuCine.model.Ticket;
import com.upc.TuCine.TuCine.repository.ContentRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface RateAfterWatchService {
    void rateMovie(Ticket ticket, String rating);
}