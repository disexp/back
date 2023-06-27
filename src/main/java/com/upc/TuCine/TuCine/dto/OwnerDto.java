package com.upc.TuCine.TuCine.dto;

import com.upc.TuCine.TuCine.model.Person;
import lombok.Data;

@Data
public class OwnerDto {
    private Integer id;
    private String bankAccount;
    private Person person;
}
