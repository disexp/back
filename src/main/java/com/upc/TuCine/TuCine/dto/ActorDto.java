package com.upc.TuCine.TuCine.dto;

import com.upc.TuCine.TuCine.model.Film;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ActorDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String biography;
    private LocalDate birthday;
    private List<Film> films;
}
