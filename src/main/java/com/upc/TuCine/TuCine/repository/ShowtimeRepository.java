package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    //Found all showtimes by film_id
    List<Showtime> findAllByFilm_id(Integer film_id);
    List<Showtime> findAllByBusiness_id(Integer business_id);
    Optional<Showtime> findByDateAndTime(LocalDate date, LocalTime time);

}
