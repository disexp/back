package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto createCustomer(CustomerDto customerDto);
}
