package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    List<Showtime> findAllByFilm_id(Integer id);
}
