package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    boolean existsPromotionByTitle(String title);
}
