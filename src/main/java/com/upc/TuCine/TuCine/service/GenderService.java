package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.GenderDto;

import java.util.List;

public interface GenderService {

    List<GenderDto> getAllGenders();

    GenderDto createGender(GenderDto genderDto);

}
