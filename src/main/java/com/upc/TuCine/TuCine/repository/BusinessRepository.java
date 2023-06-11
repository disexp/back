package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business,Integer> {
    boolean existsBusinessByName(String name);
    boolean existsBusinessByRuc(String ruc);
    boolean existsBusinessByEmail(String email);
}
