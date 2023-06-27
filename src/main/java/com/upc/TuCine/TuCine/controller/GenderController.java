package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.GenderDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Gender;
import com.upc.TuCine.TuCine.repository.GenderRepository;
import com.upc.TuCine.TuCine.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping ("/api/TuCine/v1")
public class GenderController {
    @Autowired
    private GenderService genderService;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/genders
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/genders")
    public ResponseEntity<List<GenderDto>> getAllGenders() {
        return new ResponseEntity<>(genderService.getAllGenders(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/genders
    //Method: POST
    @Transactional
    @PostMapping("/genders")
    public ResponseEntity<GenderDto> createGender(@RequestBody GenderDto genderDto){
        genderValidate(genderDto);
        existsGenderByName(genderDto.getName());
        return new ResponseEntity<GenderDto>(genderService.createGender(genderDto), HttpStatus.CREATED);
    }

    void genderValidate(GenderDto gender){
        if(gender.getName()==null || gender.getName().isEmpty()){
            throw new ValidationException("El nombre es obligatorio");
        }
    }

    void existsGenderByName(String name) {
        if (genderService.existsGenderByName(name)) {
            throw new ValidationException("No se puede registrar el genero, ya existe uno con ese nombre");
        }
    }
}
