package com.upc.TuCine.TuCine.controller;

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
    @Transactional(readOnly = true)
    @GetMapping("/businesses/{id}/businessTypes")
    public ResponseEntity<List<BusinessType>> getAllBusinessTypesByBusinessId(@PathVariable("id") Integer id) {
        Business business = businessRepository.findById(id).orElse(null); // Obtener el business por su ID
        if (business == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el business
        }
        return new ResponseEntity<List<BusinessType>>(business.getBusinessTypes(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: POST
    @Transactional
    @PostMapping("/businesses")
    public ResponseEntity<Business> createBusiness(@RequestBody Business business){
        return new ResponseEntity<Business>(businessRepository.save(business), HttpStatus.CREATED);
    }
}
