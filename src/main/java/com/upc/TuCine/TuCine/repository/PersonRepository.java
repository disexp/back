package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findById(Long personId);
}




