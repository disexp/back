package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.BusinessDto;
import com.upc.TuCine.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.TuCine.dto.ShowtimeDto;
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

    //Get all Showtimes by Business Id
    //URL: http://localhost:8080/api/TuCine/v1/businesses/{id}/showtimes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses/{id}/showtimes")
    public ResponseEntity<List<ShowtimeDto>> getAllShowtimesByBusinessId(@PathVariable("id") Integer id) {
        List<ShowtimeDto> showtimeDtoList = businessService.getAllShowtimesByBusinessId(id);
        if (showtimeDtoList == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el business
        }
        return new ResponseEntity<>(showtimeDtoList, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: POST
    @Transactional
    @PostMapping("/businesses")
    public ResponseEntity<BusinessDto> createBusiness(@RequestBody BusinessDto businessDto){
        return new ResponseEntity<>(businessService.createBusiness(businessDto), HttpStatus.CREATED);
    }



}
