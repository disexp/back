package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.GenderDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Gender;
import com.upc.TuCine.TuCine.repository.GenderRepository;
import com.upc.TuCine.TuCine.service.GenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private ModelMapper modelMapper;

    GenderServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private GenderDto EntityToDto(Gender gender){
        return modelMapper.map(gender, GenderDto.class);
    }

    private Gender DtoToEntity(GenderDto genderDto){
        return modelMapper.map(genderDto,Gender.class);
    }

    @Override
    public List<GenderDto> getAllGenders() {
        List<Gender> genders= genderRepository.findAll();
        return genders.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public GenderDto createGender(GenderDto genderDto) {
        genderValidate(genderDto);
        existsGenderByName(genderDto.getName());

       Gender gender = DtoToEntity(genderDto);
       return EntityToDto(genderRepository.save(gender));
    }
    void genderValidate(GenderDto gender){
        if(gender.getName()==null || gender.getName().isEmpty()){
            throw new ValidationException("El nombre es obligatorio");
        }
    }

    void existsGenderByName(String name) {
        if (genderRepository.existsGenderByName(name)) {
            throw new ValidationException("No se puede registrar el genero, ya existe uno con ese nombre");
        }
    }
}
