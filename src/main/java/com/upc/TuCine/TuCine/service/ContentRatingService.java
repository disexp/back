package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.ContentRatingDto;

import java.util.List;

public interface ContentRatingService {

    List<ContentRatingDto> getAllContentRatings();

    ContentRatingDto createContentRating(ContentRatingDto contentRatingDto);

    ContentRatingDto getContentRatingById(Integer id);


}
