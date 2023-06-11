package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Person;
import com.upc.TuCine.TuCine.model.TypeUser;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons/{Id}/typeUser")
    public ResponseEntity<Person> getUserById(@PathVariable("personId") Long personId) {
        Optional<Person> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            // Accede al objeto TypeUser relacionado
            TypeUser typeUser = person.getTypeUser_id();
            // Realiza las operaciones necesarias con el objeto TypeUser
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: POST
    @Transactional
    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.CREATED);
    }
}
