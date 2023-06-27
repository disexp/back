package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.ActorDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Actor;
import com.upc.TuCine.TuCine.repository.ActorRepository;
import com.upc.TuCine.TuCine.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping ("/api/TuCine/v1")
public class ActorController {

    @Autowired
    private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/actors
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/actors")
    public ResponseEntity<List<ActorDto>> getAllActors() {
        return new ResponseEntity<List<ActorDto>>(actorService.getAllActors(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/actors
    //Method: POST
    @Transactional
    @PostMapping("/actors")
    public ResponseEntity<ActorDto> createActor(@RequestBody ActorDto actorDto){
        existActorByFirstName(actorDto.getFirstName(),actorDto.getLastName());
        validateActor(actorDto);
        return new ResponseEntity<ActorDto>(actorService.createActor(actorDto), HttpStatus.CREATED);
    }

    void validateActor(ActorDto actor) {
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
        if (actorService.existsByFirstNameAndLastName(firstName,lastName)) {
            throw new ValidationException("Ya existe un actor con el nombre " + firstName + " " + lastName);
        }
    }
}
