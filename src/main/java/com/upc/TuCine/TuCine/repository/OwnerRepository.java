package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Integer> {
    boolean existsOwnerByBankAccount(String accountBank);
}
