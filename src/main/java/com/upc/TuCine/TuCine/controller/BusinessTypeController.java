package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.BusinessType;
import com.upc.TuCine.TuCine.repository.BusinessTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://backend-tucine-production.up.railway.app")
@RequestMapping("/api/TuCine/v1")
public class BusinessTypeController {

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    public BusinessTypeController(BusinessTypeRepository businessTypeRepository) {
        this.businessTypeRepository = businessTypeRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/businessTypes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businessTypes")
    public ResponseEntity<List<BusinessType>> getAllBusinessTypes() {
        return new ResponseEntity<List<BusinessType>>(businessTypeRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businessTypes
    //Method: POST
    @Transactional
    @PostMapping("/businessTypes")
    public ResponseEntity<BusinessType> createBusinessType(@RequestBody BusinessType businessType){
        validateBusinessType(businessType);
        existsBusinessTypeByName(businessType.getName());
        return new ResponseEntity<BusinessType>(businessTypeRepository.save(businessType), HttpStatus.CREATED);
    }

    void validateBusinessType(BusinessType businessType) {
        if (businessType.getName() == null || businessType.getName().isEmpty()) {
            throw new ValidationException("El nombre del tipo de negocio no puede estar vacío");
        }
    }

    void existsBusinessTypeByName(String name) {
        if (businessTypeRepository.existsBusinessTypeByName(name)) {
            throw new ValidationException("Ya hay un tipo de negocio que existe con este nombre");
        }
    }
}
