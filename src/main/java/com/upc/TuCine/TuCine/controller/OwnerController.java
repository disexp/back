package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Owner;
import com.upc.TuCine.TuCine.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TuCine/v1")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/owners
    //Method: POST
    @Transactional
    @PostMapping("/owners")
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
        return new ResponseEntity<Owner>(ownerRepository.save(owner), HttpStatus.CREATED);
    }
}
