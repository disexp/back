package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Actor;
import com.upc.TuCine.TuCine.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping ("/api/TuCine/v1")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/actors
    //Method: POST
    @Transactional
    @PostMapping("/actors")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor){
        return new ResponseEntity<Actor>(actorRepository.save(actor), HttpStatus.CREATED);
    }
}
