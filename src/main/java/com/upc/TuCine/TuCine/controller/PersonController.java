package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.*;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://backend-tucine-production.up.railway.app")
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

    //URL: http://localhost:8080/api/TuCine/v1/persons/{id}/typeUser
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons/{id}/typeUser")
    public ResponseEntity<TypeUser> getTypeUserByPersonId(@PathVariable("id") Integer id) {
        Person person = personRepository.findById(id).orElse(null); // Obtener el person por su ID
        if (person == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el person
        }
        return new ResponseEntity<TypeUser>(person.getTypeUser_id(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: POST
    @Transactional
    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        validatePerson(person);
        existsByPersonEmail(person.getEmail());
        existByPersonDni(person.getNumberDni());
        return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.CREATED);
    }

    void validatePerson(Person person) {
        if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
            throw new ValidationException("El nombre de la persona es obligatorio");
        }
        if(person.getLastName()==null || person.getLastName().isEmpty()){
            throw new ValidationException("El apellido de la persona es obligatorio");
        }
        if(person.getNumberDni()==null || person.getNumberDni().isEmpty()){
            throw new ValidationException("El DNI de la persona es obligatorio");
        }
        if(person.getEmail()==null || person.getEmail().isEmpty()){
            throw new ValidationException("El email de la persona es obligatorio");
        }
        if(person.getPassword()==null || person.getPassword().isEmpty()){
            throw new ValidationException("La contraseña de la persona es obligatorio");
        }
        if(person.getBirthdate()==null){
            throw new ValidationException("La fecha de nacimiento de la persona es obligatorio");
        }
        if(person.getGender_id()==null ){
            throw new ValidationException("El género de la persona es obligatorio");
        }
        if(person.getPhone()==null || person.getPhone().isEmpty()){
            throw new ValidationException("El teléfono de la persona es obligatorio");
        }
        if(person.getTypeUser_id()==null){
            throw new ValidationException("El tipo de usuario de la persona es obligatorio");
        }
    }

    void existsByPersonEmail(String email) {
        if (personRepository.existsPersonByEmail(email)) {
            throw new ValidationException("Ya existe una persona registrada con ese email");
        }
    }

    void existByPersonDni(String dni) {
        if (personRepository.existsPersonByNumberDni(dni)) {
            throw new ValidationException("Ya existe una persona registrada con ese DNI");
        }
    }
}
