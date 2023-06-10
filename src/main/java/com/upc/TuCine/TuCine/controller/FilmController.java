package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Category;
import com.upc.TuCine.TuCine.model.ContentRating;
import com.upc.TuCine.TuCine.model.Film;
import com.upc.TuCine.TuCine.repository.CategoryRepository;
import com.upc.TuCine.TuCine.repository.ContentRatingRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ContentRatingRepository contentRatingRepository;

    public FilmController(FilmRepository filmRepository, CategoryRepository categoryRepository, ContentRatingRepository contentRatingRepository) {
        this.filmRepository = filmRepository;
        this.categoryRepository = categoryRepository;
        this.contentRatingRepository = contentRatingRepository;
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

        ContentRating contentRating= contentRatingRepository.findById(film.getContentRating().getId()).orElse(null); // Obtener el contentRating por su ID
        film.setContentRating(contentRating);
        return new ResponseEntity<>(filmRepository.save(film), HttpStatus.CREATED);
    }


    // URL: http://localhost:8080/api/TuCine/v1/films/

    // URL: http://localhost:8080/api/TuCine/v1/films/{filmId}/categories/{categoryId}
    // Method: POST
    @Transactional
    @PostMapping("/films/{filmId}/categories/{categoryId}")
    public ResponseEntity<?> addCategoryToFilm(@PathVariable("filmId") Integer filmId, @PathVariable("categoryId") Integer categoryId) {
        Film film = filmRepository.findById(filmId).orElse(null); // Obtener el film por su ID
        Category category = categoryRepository.findById(categoryId).orElse(null); // Obtener la categoría por su ID

        if (film == null || category == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film o la categoría
        }
        film.getCategories().add(category); // Agregar la categoría al film
        filmRepository.save(film); // Guardar los cambios en la base de datos
        return ResponseEntity.ok().build();
    }

    //Get content rating by Film Id
    //URL: http://localhost:8080/api/TuCine/v1/films/{id}/contentRating
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{id}/contentRating")
    public ResponseEntity<ContentRating> getContentRatingByFilmId(@PathVariable("id") Integer id) {
        Film film = filmRepository.findById(id).orElse(null); // Obtener el film por su ID
        if (film == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<ContentRating>(film.getContentRating(), HttpStatus.OK);
    }

}
