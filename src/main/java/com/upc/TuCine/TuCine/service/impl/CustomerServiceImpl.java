package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.CustomerDto;
import com.upc.TuCine.TuCine.model.Category;
import com.upc.TuCine.TuCine.model.Customer;
import com.upc.TuCine.TuCine.repository.CustomerRepository;
import com.upc.TuCine.TuCine.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    CustomerServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private CustomerDto EntityToDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer DtoToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = DtoToEntity(customerDto);
        return EntityToDto(customerRepository.save(customer));
    }
}
