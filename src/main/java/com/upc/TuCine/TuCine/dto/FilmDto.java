package com.upc.TuCine.TuCine.dto;

import com.upc.TuCine.TuCine.model.Actor;
import com.upc.TuCine.TuCine.model.Category;
import com.upc.TuCine.TuCine.model.ContentRating;
import lombok.Data;

import java.util.List;

@Data
public class FilmDto {

    private Integer id;
    private String title;
    private Integer year;
    private String synopsis;
    private String poster;
    private String trailer;
    private Integer duration;
    private ContentRating contentRating;
    private List<Actor> actors;
    private List<Category> categories;
}

