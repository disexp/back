package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.BusinessType;
import com.upc.TuCine.TuCine.repository.BusinessTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TuCine/v1")
public class BusinessTypeController {

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    public BusinessTypeController(BusinessTypeRepository businessTypeRepository) {
        this.businessTypeRepository = businessTypeRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/businessTypes
    //Method: POST
    @Transactional
    @PostMapping("/businessTypes")
    public ResponseEntity<BusinessType> createBusinessType(@RequestBody BusinessType businessType){
        return new ResponseEntity<BusinessType>(businessTypeRepository.save(businessType), HttpStatus.CREATED);
    }

}
