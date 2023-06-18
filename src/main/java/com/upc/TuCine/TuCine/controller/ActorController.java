package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Actor;
import com.upc.TuCine.TuCine.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://backend-tucine-production.up.railway.app")
@RequestMapping ("/api/TuCine/v1")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/actors
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getAllActors() {
        return new ResponseEntity<List<Actor>>(actorRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/actors
    //Method: POST
    @Transactional
    @PostMapping("/actors")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor){
        existActorByFirstName(actor.getFirstName(),actor.getLastName());
        validateActor(actor);
        return new ResponseEntity<Actor>(actorRepository.save(actor), HttpStatus.CREATED);
    }

    void validateActor(Actor actor) {
        if (actor.getFirstName() == null || actor.getFirstName().isEmpty()) {
            throw new ValidationException("El nombre es obligatorio");
        }
        if (actor.getLastName() == null || actor.getLastName().isEmpty()) {
            throw new ValidationException("El apellido es obligatorio");
        }
        if (actor.getBirthday() == null) {
            throw new ValidationException("La fecha de nacimiento es obligatoria");
        }
    }

    void existActorByFirstName(String firstName,String lastName){
        if (actorRepository.existsByFirstNameAndLastName(firstName,lastName)) {
            throw new ValidationException("Ya existe un actor con el nombre " + firstName + " " + lastName);
        }
    }
}
