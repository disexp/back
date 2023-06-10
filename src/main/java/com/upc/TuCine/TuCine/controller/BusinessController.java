package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Business;
import com.upc.TuCine.TuCine.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TuCine/v1")
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    public BusinessController(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses")
    public ResponseEntity<List<Business>> getAllBusinesses() {
        return new ResponseEntity<List<Business>>(businessRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: POST
    @Transactional
    @PostMapping("/businesses")
    public ResponseEntity<Business> createBusiness(@RequestBody Business business){
        return new ResponseEntity<Business>(businessRepository.save(business), HttpStatus.CREATED);
    }
}
