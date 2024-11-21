package com.upc.TuCine.TuCine;

import com.upc.TuCine.TuCine.model.ContentRating;
import com.upc.TuCine.TuCine.model.Showtime;
import com.upc.TuCine.TuCine.model.Ticket;
import com.upc.TuCine.TuCine.repository.ContentRatingRepository;
import com.upc.TuCine.TuCine.service.RateAfterWatchService;
import com.upc.TuCine.TuCine.service.impl.RateAfterWatchServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.*;

public class RateAfterWatchTest {
    @Mock
    private ContentRatingRepository contentRatingRepository;

    @InjectMocks
    private RateAfterWatchServiceImpl rateAfterWatchService;


    public RateAfterWatchTest(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testRateAfterWatch(){

        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setNumberSeats(2);
        ticket.setTotalPrice(20.0f);
        ticket.setStatus("Watched");

        Showtime showtime = new Showtime();
        showtime.setId(1);
        showtime.setDate(LocalDate.now());
        showtime.setTime(LocalTime.parse("10:00"));
        showtime.setPrice(10.0f);

        ticket.setShowtime(showtime);

        when(contentRatingRepository.save(any())).thenReturn(new ContentRating());

        rateAfterWatchService.rateMovie(ticket,"Excelente");

        verify(contentRatingRepository,times(1)).save(any());
    }

}
