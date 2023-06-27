package com.upc.TuCine.TuCine.dto;

import com.upc.TuCine.TuCine.model.Person;
import lombok.Data;

@Data
public class CustomerDto {
    private Integer id;
    private Person person;
}
