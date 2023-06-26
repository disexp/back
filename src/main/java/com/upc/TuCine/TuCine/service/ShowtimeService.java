package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.model.Showtime;

import java.util.List;

public interface ShowtimeService {
    List<Showtime> findAllByFilm_id(Integer id);
}
