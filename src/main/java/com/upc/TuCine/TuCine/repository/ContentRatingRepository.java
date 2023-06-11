package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.ContentRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRatingRepository extends JpaRepository<ContentRating,Integer> {

    boolean existsContentRatingByName(String name);
}
