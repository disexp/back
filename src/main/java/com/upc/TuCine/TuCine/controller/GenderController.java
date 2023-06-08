package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Gender;
import com.upc.TuCine.TuCine.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping ("/api/TuCine/v1")
public class GenderController {
    @Autowired
    private GenderRepository genderRepository;

    public GenderController(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/genders
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/genders")
    public ResponseEntity<List<Gender>> getAllGenders() {
        return new ResponseEntity<List<Gender>>(genderRepository.findAll(), HttpStatus.OK);
    }

}
