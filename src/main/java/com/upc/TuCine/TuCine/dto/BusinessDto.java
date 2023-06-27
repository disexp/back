package com.upc.TuCine.TuCine.dto;

import com.upc.TuCine.TuCine.model.BusinessType;
import com.upc.TuCine.TuCine.model.Owner;
import lombok.Data;

@Data
public class BusinessDto {
    private Integer id;
    private String name;
    private String socialReason;
    private String ruc;
    private String phone;
    private String email;
    private String imageLogo;
    private String imageBanner;
    private String description;
    private String dateAttention;
    private String address;
    private String referenceAddress;
    private Owner owner;
    private BusinessType businessType;
}
