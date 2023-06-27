package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons")
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons/{id}/typeUser
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons/{id}/typeUser")
    public ResponseEntity<TypeUserDto> getTypeUserByPersonId(@PathVariable("id") Integer id) {
        TypeUserDto typeUserDto= personService.getTypeUserByPersonId(id);
        if (typeUserDto == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(typeUserDto, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: POST
    @Transactional
    @PostMapping("/persons")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){
        validatePerson(personDto);
        existsByPersonEmail(personDto.getEmail());
        existByPersonDni(personDto.getNumberDni());
        return new ResponseEntity<>(personService.createPerson(personDto), HttpStatus.CREATED);
    }

    void validatePerson(PersonDto person) {
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
        if(person.getGender()==null ){
            throw new ValidationException("El género de la persona es obligatorio");
        }
        if(person.getPhone()==null || person.getPhone().isEmpty()){
            throw new ValidationException("El teléfono de la persona es obligatorio");
        }
        if(person.getTypeUser()==null){
            throw new ValidationException("El tipo de usuario de la persona es obligatorio");
        }
    }

    void existsByPersonEmail(String email) {
        if (personService.existsByPersonEmail(email)) {
            throw new ValidationException("Ya existe una persona registrada con ese email");
        }
    }

    void existByPersonDni(String dni) {
        if (personService.existsPersonByNumberDni(dni)) {
            throw new ValidationException("Ya existe una persona registrada con ese DNI");
        }
    }
}
