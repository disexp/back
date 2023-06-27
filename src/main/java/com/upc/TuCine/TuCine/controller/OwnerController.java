package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.OwnerDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Owner;
import com.upc.TuCine.TuCine.repository.OwnerRepository;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import com.upc.TuCine.TuCine.service.OwnerService;
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
    private OwnerService ownerService;

    OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/owners
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return new ResponseEntity<>(ownerService.getAllOwners(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/owners
    //Method: POST
    @Transactional
    @PostMapping("/owners")
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto ownerDto){
        validateOwner(ownerDto);
        existsPersonById(ownerDto.getPerson().getId());
        existsOwnerByAccountNumber(ownerDto.getBankAccount());
        return new ResponseEntity<OwnerDto>(ownerService.createOwner(ownerDto), HttpStatus.CREATED);
    }

    void validateOwner(OwnerDto owner) {
        if (owner.getPerson() == null) {
            throw new ValidationException("El person es obligatorio");
        }
        if(owner.getBankAccount()==null || owner.getBankAccount().isEmpty()){
            throw new ValidationException("La cuenta bancaria es obligatoria");
        }
    }

    void existsOwnerByAccountNumber(String accountNumber) {
        if (ownerService.existsOwnerByAccountNumber(accountNumber)) {
            throw new ValidationException("Ya existe un owner con el numero de cuenta: " + accountNumber);
        }
    }

    void existsPersonById(Integer id) {
        if (!ownerService.existsPersonById(id)) {
            throw new ValidationException("No existe el person con ID: " + id+" para crear el owner");
        }
    }

}
