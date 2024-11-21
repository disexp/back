package com.upc.TuCine.TuCine.service.impl;


import com.upc.TuCine.TuCine.model.ContentRating;
import com.upc.TuCine.TuCine.model.Ticket;
import com.upc.TuCine.TuCine.repository.ContentRatingRepository;
import com.upc.TuCine.TuCine.service.RateAfterWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateAfterWatchServiceImpl implements RateAfterWatchService {

    @Autowired
    private ContentRatingRepository contentRatingRepository;

    @Override
    public void rateMovie(Ticket ticket, String rating) {
        if ("Watched".equals(ticket.getStatus())) {
            ContentRating contentRating = new ContentRating();
            contentRating.setTicket(ticket);
            contentRating.setRating(rating);
            contentRatingRepository.save(contentRating);
        }
    }
}