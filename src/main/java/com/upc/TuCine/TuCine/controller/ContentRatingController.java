package com.upc.TuCine.TuCine.controller;


import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.ContentRating;
import com.upc.TuCine.TuCine.repository.ContentRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TuCine/v1")

public class ContentRatingController {

    @Autowired
    private ContentRatingRepository contentRatingRepository;

    public ContentRatingController(ContentRatingRepository contentRatingRepository) {
        this.contentRatingRepository = contentRatingRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/contentRatings
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/contentRatings")
    public ResponseEntity<List<ContentRating>> getAllContentRatings() {
        return new ResponseEntity<List<ContentRating>>(contentRatingRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/contentRatings/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/contentRatings/{id}")
    public ResponseEntity<ContentRating> getContentRatingById(@PathVariable("id") Integer id) {
        return new ResponseEntity<ContentRating>(contentRatingRepository.findById(id).get(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/contentRatings
    //Method: POST
    @Transactional
    @PostMapping("/contentRatings")
    public ResponseEntity<ContentRating> createContentRating(@RequestBody ContentRating contentRating){
        validateContentRating(contentRating);
        existsContentRatingByName(contentRating.getName());
        return new ResponseEntity<ContentRating>(contentRatingRepository.save(contentRating), HttpStatus.CREATED);
    }

    void validateContentRating(ContentRating contentRating) {
        if (contentRating.getName() == null || contentRating.getName().isEmpty()) {
            throw new ValidationException("El nombre de la clasificacion es requerido");
        }
        if(contentRating.getDescription() == null || contentRating.getDescription().isEmpty()){
            throw new ValidationException("La descripcion de la clasificacion es requerida");
        }
    }

    void existsContentRatingByName(String name) {
        if (contentRatingRepository.existsContentRatingByName(name)) {
            throw new ValidationException("No se puede registrar la clasificación, ya existe uno con ese nombre");
        }
    }
    

}
