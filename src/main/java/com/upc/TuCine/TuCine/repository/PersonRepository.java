package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
}

