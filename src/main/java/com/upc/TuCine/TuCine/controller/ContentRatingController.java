package com.upc.TuCine.TuCine.controller;


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



}
