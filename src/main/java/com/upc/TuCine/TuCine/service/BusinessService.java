package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.BusinessDto;
import com.upc.TuCine.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.TuCine.model.Business;

import java.util.List;

public interface BusinessService {

    BusinessDto createBusiness(BusinessDto businessDto);

    List<BusinessDto> getAllBusiness();

    BusinessDto getBusinessById(Integer id);

    BusinessTypeDto getBusinessTypeByBusinessId(Integer id);

    boolean existsByBusinessName(String name);

    boolean existsByBusinessRuc(String ruc);

    boolean existsByBusinessEmail(String email);

}
