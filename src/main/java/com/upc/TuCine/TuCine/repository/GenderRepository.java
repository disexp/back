package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender,Integer> {
    boolean existsGenderByName(String name);
}
