package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.TypeUser;
import com.upc.TuCine.TuCine.repository.TypeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TuCine/v1")
public class TypeUserController {

    @Autowired
    private TypeUserRepository typeUserRepository;

    public TypeUserController(TypeUserRepository typeUserRepository) {
        this.typeUserRepository = typeUserRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/typeUsers
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/typeUsers")
    public ResponseEntity<List<TypeUser>> getAllTypeUsers() {
        return new ResponseEntity<List<TypeUser>>(typeUserRepository.findAll(), HttpStatus.OK);
    }
}
