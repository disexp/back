package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.CustomerDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/customers
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/customers
    //Method: POST
    @Transactional
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<CustomerDto>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }


}
