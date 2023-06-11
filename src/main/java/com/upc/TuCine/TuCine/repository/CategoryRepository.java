package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

    boolean existsByName(String name);
}
