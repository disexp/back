package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Customer;
import com.upc.TuCine.TuCine.repository.CustomerRepository;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TuCine/v1")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PersonRepository personRepository;

    public CustomerController(CustomerRepository customerRepository, PersonRepository personRepository) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/customers
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<List<Customer>>(customerRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/customers
    //Method: POST
    @Transactional
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        validateCustomer(customer);
        return new ResponseEntity<Customer>(customerRepository.save(customer), HttpStatus.CREATED);
    }

    private void validateCustomer(Customer customer) {
        if (customer.getPerson_id() == null) {
            throw new ValidationException("La persona es obligatoria");
        }
    }


}
