package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.BusinessDto;
import com.upc.TuCine.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.TuCine.model.Business;
import com.upc.TuCine.TuCine.model.BusinessType;
import com.upc.TuCine.TuCine.repository.BusinessRepository;
import com.upc.TuCine.TuCine.service.BusinessService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ModelMapper modelMapper;

    BusinessServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    public BusinessDto EntityToDto(Business business){
        return modelMapper.map(business, BusinessDto.class);
    }

    public Business DtoToEntity(BusinessDto businessDto){
        return modelMapper.map(businessDto, Business.class);
    }

    public BusinessTypeDto convertBusinessTypeToDto(BusinessType businessType){
        return modelMapper.map(businessType, BusinessTypeDto.class);
    }

    @Override
    public BusinessDto createBusiness(BusinessDto businessDto) {
        Business business = DtoToEntity(businessDto);
        return EntityToDto(businessRepository.save(business));
    }

    @Override
    public List<BusinessDto> getAllBusiness() {
        List<Business> businesses = businessRepository.findAll();
        return businesses.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BusinessDto getBusinessById(Integer id) {
        Business business = businessRepository.findById(id).orElse(null);
        if (business == null) {
            return null;
        }
        return EntityToDto(business);
    }


    @Override
    public BusinessTypeDto getBusinessTypeByBusinessId(Integer id) {
        Business business = businessRepository.getById(id);
        if (business == null) {
            return null;
        }
        BusinessType businessType = business.getBusinessType_id();
        return convertBusinessTypeToDto(businessType);
    }


    @Override
    public boolean existsByBusinessName(String name) {
        return businessRepository.existsBusinessByName(name);
    }

    @Override
    public boolean existsByBusinessRuc(String ruc) {
        return businessRepository.existsBusinessByRuc(ruc);
    }

    @Override
    public boolean existsByBusinessEmail(String email) {
        return businessRepository.existsBusinessByEmail(email);
    }
}
