package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeUserRepository extends JpaRepository<TypeUser,Integer> {
    boolean existsTypeUserByName(String name);

}
