package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.BusinessDto;
import com.upc.TuCine.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }
    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses")
    public ResponseEntity<List<BusinessDto>> getAllBusinesses() {
        return new ResponseEntity<>(businessService.getAllBusiness(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses/{id}")
    public ResponseEntity<BusinessDto> getBusinessById(@PathVariable(value = "id") Integer id) {
        BusinessDto businessDto = businessService.getBusinessById(id);
        if (businessDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(businessDto, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses/{id}/businessTypes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses/{id}/businessTypes")
    public ResponseEntity<BusinessTypeDto> getBusinessTypeByBusinessId(@PathVariable("id") Integer id) {
        BusinessTypeDto businessTypeDto = businessService.getBusinessTypeByBusinessId(id);
        if (businessTypeDto == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el business
        }
        return new ResponseEntity<>(businessTypeDto, HttpStatus.OK);
    }


    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: POST
    @Transactional
    @PostMapping("/businesses")
    public ResponseEntity<BusinessDto> createBusiness(@RequestBody BusinessDto businessDto){
        validateBusiness(businessDto);
        existsByBusinessName(businessDto.getName());
        existsByBusinessRuc(businessDto.getRuc());
        existsByBusinessEmail(businessDto.getEmail());
        return new ResponseEntity<>(businessService.createBusiness(businessDto), HttpStatus.CREATED);
    }

    public void validateBusiness(BusinessDto business) {
        if (business.getName() == null || business.getName().isEmpty()) {
            throw new ValidationException("El nombre de negocio es obligatorio");
        }
        if(business.getSocialReason()==null || business.getSocialReason().isEmpty()){
            throw new ValidationException("La razón social es obligatoria");
        }
        if (business.getRuc() == null || business.getRuc().isEmpty()) {
            throw new ValidationException("El RUC es obligatorio");
        }
        if (business.getEmail() == null || business.getEmail().isEmpty()) {
            throw new ValidationException("El correo es obligatorio");
        }
        if (business.getAddress() == null || business.getAddress().isEmpty()) {
            throw new ValidationException("La dirección es obligatoria");
        }
        if (business.getPhone() == null || business.getPhone().isEmpty()) {
            throw new ValidationException("El teléfono es obligatorio");
        }
        if(business.getImageLogo()==null || business.getImageLogo().isEmpty()){
            throw new ValidationException("La imagen es obligatoria");
        }
        if(business.getImageBanner()==null || business.getImageBanner().isEmpty()){
            throw new ValidationException("La imagen es obligatoria");
        }
        if(business.getDescription()==null || business.getDescription().isEmpty()){
            throw new ValidationException("La descripción es obligatoria");
        }
        if(business.getDateAttention()==null || business.getDateAttention().isEmpty()){
            throw new ValidationException("La fecha de atención es obligatoria");
        }
        if(business.getReferenceAddress()==null || business.getReferenceAddress().isEmpty()){
            throw new ValidationException("La referencia de la dirección es obligatoria");
        }
        if(business.getBusinessType()==null){
            throw new ValidationException("El tipo de negocio es obligatorio");
        }
        if(business.getOwner()==null){
            throw new ValidationException("El dueño es obligatorio");
        }
    }

    public void existsByBusinessName(String businessName) {
        if (businessService.existsByBusinessName(businessName)) {
            throw new ValidationException("El nombre de negocio ya existe");
        }
    }
    public void existsByBusinessRuc(String businessRuc) {
        if (businessService.existsByBusinessRuc(businessRuc)) {
            throw new ValidationException("Un Business con ese RUC ya existe");
        }
    }
    public void existsByBusinessEmail(String businessEmail) {
        if (businessService.existsByBusinessEmail(businessEmail)) {
            throw new ValidationException("Un Business con ese correo ya existe");
        }
    }

}
