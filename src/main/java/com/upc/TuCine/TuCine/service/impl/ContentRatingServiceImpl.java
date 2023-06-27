package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.ContentRatingDto;
import com.upc.TuCine.TuCine.model.ContentRating;
import com.upc.TuCine.TuCine.repository.ContentRatingRepository;
import com.upc.TuCine.TuCine.service.ContentRatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentRatingServiceImpl implements ContentRatingService {

    @Autowired
    private ContentRatingRepository contentRatingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ContentRatingServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private ContentRatingDto EntityToDto(ContentRating contentRating){
        return modelMapper.map(contentRating, ContentRatingDto.class);
    }

    private ContentRating DtoToEntity(ContentRatingDto contentRatingDto){
        return modelMapper.map(contentRatingDto, ContentRating.class);
    }

    @Override
    public List<ContentRatingDto> getAllContentRatings() {
        List<ContentRating> contentRatings = contentRatingRepository.findAll();
        return contentRatings.stream()
                .map(this::EntityToDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ContentRatingDto createContentRating(ContentRatingDto contentRatingDto) {
        ContentRating contentRating = DtoToEntity(contentRatingDto);
        return EntityToDto(contentRatingRepository.save(contentRating));
    }

    @Override
    public ContentRatingDto getContentRatingById(Integer id) {
        ContentRating contentRating = contentRatingRepository.findById(id).orElse(null);
        if (contentRating == null) {
            return null;
        }
        return EntityToDto(contentRating);
    }

    @Override
    public boolean existsContentRatingByName(String name) {
        return contentRatingRepository.existsContentRatingByName(name);
    }
}
