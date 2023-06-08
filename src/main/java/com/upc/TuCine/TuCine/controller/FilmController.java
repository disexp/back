package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Film;
import com.upc.TuCine.TuCine.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TuCine/v1")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/films
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilms() {
        return new ResponseEntity<List<Film>>(filmRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/films
    //Method: POST
    @Transactional
    @PostMapping("/films")
    public ResponseEntity<Film> createFilm(@RequestBody Film film){
        return new ResponseEntity<Film>(filmRepository.save(film), HttpStatus.CREATED);
    }

}
