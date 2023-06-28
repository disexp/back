package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.*;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.FilmService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/TuCine/v1")
public class FilmController {

    @Autowired
    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    //URL: http://localhost:8080/api/TuCine/v1/films
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films")
    public ResponseEntity<List<FilmDto>> getAllFilms() {
        return new ResponseEntity<>(filmService.getAllFilms(), HttpStatus.OK);
    }


    //URL: http://localhost:8080/api/TuCine/v1/films/{filmId}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{filmId}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable("filmId") Integer filmId) {
        FilmDto filmDto = filmService.getFilmById(filmId);
        if (filmDto == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(filmDto, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/films
    //Method: POST
    @Transactional
    @PostMapping("/films")
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto filmDto){
        FilmDto createdFilmDto= filmService.createFilm(filmDto);
        return new ResponseEntity<>(createdFilmDto, HttpStatus.CREATED);
    }

    //Get content rating by Film Id
    //URL: http://localhost:8080/api/TuCine/v1/films/{id}/contentRating
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{id}/contentRating")
    public ResponseEntity<ContentRatingDto> getContentRatingByFilmId(@PathVariable("id") Integer id) {
        ContentRatingDto contentRatingDto = filmService.getContentRatingByFilmId(id);
        if (contentRatingDto == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(contentRatingDto, HttpStatus.OK);
    }

    //Get All Categories By Film ID
    //URL: http://localhost:8080/api/TuCine/v1/films/{id}/categories
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{id}/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByFilmId(@PathVariable("id") Integer id) {
        List<CategoryDto> categoryDtoList = filmService.getAllCategoriesByFilmId(id);
        if (categoryDtoList == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    //Get all the Showtimes of a Film by Film ID
    //URL: http://localhost:8080/api/TuCine/v1/films/{id}/showtimes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{id}/showtimes")
    public ResponseEntity<List<ShowtimeDto>> getAllShowtimesByFilmId(@PathVariable("id") Integer id) {
        List<ShowtimeDto> showtimeDtoList = filmService.getAllShowtimesByFilmId(id);
        if (showtimeDtoList == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(showtimeDtoList, HttpStatus.OK);
    }

    //Get All Actors By Film ID
    //URL: http://localhost:8080/api/TuCine/v1/films/{id}/actors
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{id}/actors")
    public ResponseEntity<List<ActorDto>> getAllActorsByFilmId(@PathVariable("id") Integer id) {
        List<ActorDto> actorDtoList = filmService.getAllActorsByFilmId(id);
        if (actorDtoList == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(actorDtoList, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/films/{idFilm}/actors/{idActor}
    //Method: POST
    @Transactional
    @PostMapping("/films/{idFilm}/actors/{idActor}")
    public ResponseEntity<String> addActorToFilm(@PathVariable(value = "idFilm") Integer idFilm, @PathVariable(value = "idActor") Integer idActor){
        filmService.addActorToFilm(idFilm, idActor);
        return ResponseEntity.ok("Actor added to film successfully.");
    }

    // URL: http://localhost:8080/api/TuCine/v1/films/{filmId}/categories/{categoryId}
    // Method: POST
    @Transactional
    @PostMapping("/films/{filmId}/categories/{categoryId}")
    public ResponseEntity<String> addCategoryToFilm(@PathVariable("filmId") Integer filmId, @PathVariable("categoryId") Integer categoryId) {
        filmService.addCategoryToFilm(filmId, categoryId);
        return ResponseEntity.ok("Category added to film successfully.");
    }




}
