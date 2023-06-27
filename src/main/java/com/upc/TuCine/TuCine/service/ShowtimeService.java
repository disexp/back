package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.ShowtimeDto;
import com.upc.TuCine.TuCine.model.Showtime;

import java.util.List;

public interface ShowtimeService {

    List<ShowtimeDto>getAllShowtimes();

    ShowtimeDto getShowtimeById(Integer id);

    ShowtimeDto createShowtime(ShowtimeDto showtimeDto);

    ShowtimeDto updateShowtime(Integer id, ShowtimeDto showtimeDto);

    ShowtimeDto deleteShowtime(Integer id);


}
