package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Person;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TuCine/v1")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        return new ResponseEntity<List<Person>>(personRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: POST
    @Transactional
    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.CREATED);
    }
}
