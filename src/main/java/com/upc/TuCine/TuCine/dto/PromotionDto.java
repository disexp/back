package com.upc.TuCine.TuCine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.upc.TuCine.TuCine.model.Business;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PromotionDto {
    private Integer id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    private String description;
    private Float discount;
    private Business business;
}
