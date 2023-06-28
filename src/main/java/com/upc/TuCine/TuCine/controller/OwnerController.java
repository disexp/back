package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.OwnerDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/owners
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return new ResponseEntity<>(ownerService.getAllOwners(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/owners
    //Method: POST
    @Transactional
    @PostMapping("/owners")
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto ownerDto){
        return new ResponseEntity<OwnerDto>(ownerService.createOwner(ownerDto), HttpStatus.CREATED);
    }



}
