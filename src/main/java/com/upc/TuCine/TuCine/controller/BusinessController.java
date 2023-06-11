package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Business;
import com.upc.TuCine.TuCine.model.BusinessType;
import com.upc.TuCine.TuCine.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TuCine/v1")
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    public BusinessController(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }
    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses")
    public ResponseEntity<List<Business>> getAllBusinesses() {
        return new ResponseEntity<List<Business>>(businessRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses/{id}/businessTypes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses/{id}/businessTypes")
    public ResponseEntity<BusinessType> getBusinessTypeByBusinessId(@PathVariable("id") Integer id) {
        Business business = businessRepository.findById(id).orElse(null); // Obtener el business por su ID
        if (business == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el business
        }
        return new ResponseEntity<BusinessType>(business.getBusinessType_id(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: POST
    @Transactional
    @PostMapping("/businesses")
    public ResponseEntity<Business> createBusiness(@RequestBody Business business){
        validateBusiness(business);
        existsByBusinessName(business.getName());
        existsByBusinessRuc(business.getRuc());
        existsByBusinessEmail(business.getEmail());
        return new ResponseEntity<Business>(businessRepository.save(business), HttpStatus.CREATED);
    }

    public void validateBusiness(Business business) {
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
        if(business.getBusinessType_id()==null){
            throw new ValidationException("El tipo de negocio es obligatorio");
        }
        if(business.getOwner_id()==null){
            throw new ValidationException("El dueño es obligatorio");
        }
    }

    public void existsByBusinessName(String businessName) {
        if (businessRepository.existsBusinessByName(businessName)) {
            throw new ValidationException("El nombre de negocio ya existe");
        }
    }
    public void existsByBusinessRuc(String businessRuc) {
        if (businessRepository.existsBusinessByRuc(businessRuc)) {
            throw new ValidationException("Un Business con ese RUC ya existe");
        }
    }
    public void existsByBusinessEmail(String businessEmail) {
        if (businessRepository.existsBusinessByEmail(businessEmail)) {
            throw new ValidationException("Un Business con ese correo ya existe");
        }
    }

}
