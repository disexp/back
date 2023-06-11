package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessTypeRepository extends JpaRepository<BusinessType,Integer> {
    boolean existsBusinessTypeByName(String name);
}
