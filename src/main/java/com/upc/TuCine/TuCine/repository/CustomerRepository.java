package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
