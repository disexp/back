package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Owner;
import com.upc.TuCine.TuCine.repository.OwnerRepository;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    private OwnerRepository ownerRepository;
    @Autowired
    private PersonRepository personRepository;
    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/owners
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        return new ResponseEntity<List<Owner>>(ownerRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/owners
    //Method: POST
    @Transactional
    @PostMapping("/owners")
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
        validateOwner(owner);
        existsPersonById(owner.getPerson_id().getId());
        existsOwnerByAccountNumber(owner.getBankAccount());
        return new ResponseEntity<Owner>(ownerRepository.save(owner), HttpStatus.CREATED);
    }

    void validateOwner(Owner owner) {
        if (owner.getPerson_id() == null) {
            throw new ValidationException("El person es obligatorio");
        }
        if(owner.getBankAccount()==null || owner.getBankAccount().isEmpty()){
            throw new ValidationException("La cuenta bancaria es obligatoria");
        }
    }

    void existsOwnerByAccountNumber(String accountNumber) {
        if (ownerRepository.existsOwnerByBankAccount(accountNumber)) {
            throw new ValidationException("Ya existe un owner con el numero de cuenta: " + accountNumber);
        }
    }

    void existsPersonById(Integer id) {
        if (!personRepository.existsById(id)) {
            throw new ValidationException("No existe el person con ID: " + id+" para crear el owner");
        }
    }

}
