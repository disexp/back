package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class BusinessTypeController {

    @Autowired
    private BusinessTypeService businessTypeService;

    public BusinessTypeController(BusinessTypeService businessTypeService) {
        this.businessTypeService = businessTypeService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/businessTypes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businessTypes")
    public ResponseEntity<List<BusinessTypeDto>> getAllBusinessTypes() {
        return new ResponseEntity<>(businessTypeService.getAllBusinessTypes(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businessTypes/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businessTypes/{id}")
    public ResponseEntity<BusinessTypeDto> getBusinessTypeById(@PathVariable(value = "id") Integer id) {

        BusinessTypeDto businessTypeDto = businessTypeService.getBusinessTypeById(id);
        if (businessTypeDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(businessTypeDto, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businessTypes
    //Method: POST
    @Transactional
    @PostMapping("/businessTypes")
    public ResponseEntity<BusinessTypeDto> createBusinessType(@RequestBody BusinessTypeDto businessTypeDto){
        validateBusinessType(businessTypeDto);
        existsBusinessTypeByName(businessTypeDto.getName());
        return new ResponseEntity<>(businessTypeService.createBusinessType(businessTypeDto), HttpStatus.CREATED);
    }

    void validateBusinessType(BusinessTypeDto businessType) {
        if (businessType.getName() == null || businessType.getName().isEmpty()) {
            throw new ValidationException("El nombre del tipo de negocio no puede estar vacío");
        }
    }

    void existsBusinessTypeByName(String name) {
        if (businessTypeService.existsBusinessTypeByName(name)) {
            throw new ValidationException("Ya hay un tipo de negocio que existe con este nombre");
        }
    }
}
