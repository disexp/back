package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    boolean existsFilmByTitle(String title);
}
