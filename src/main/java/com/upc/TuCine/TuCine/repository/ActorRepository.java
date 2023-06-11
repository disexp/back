package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}