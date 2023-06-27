package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.TicketDto;
import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.TypeUser;
import com.upc.TuCine.TuCine.repository.TypeUserRepository;
import com.upc.TuCine.TuCine.service.TypeUserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")

public class TypeUserController {

    @Autowired
    private TypeUserService typeUserService;

    public TypeUserController(TypeUserService typeUserService) {
        this.typeUserService = typeUserService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/typeUsers
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/typeUsers")
    @Operation(summary = "Obtener todos los TypeUsers", description = "Obtiene la lista de todos los tipos de usuarios disponibles")
    public ResponseEntity<List<TypeUserDto>> getAllTypeUsers() {
        return new ResponseEntity<List<TypeUserDto>>(typeUserService.getAllTypeUsers(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/typeUsers
    //Method: POST
    @Transactional
    @PostMapping("/typeUsers")
    @Operation(summary = "Crear un nuevo TypeUser", description = "Crea un nuevo tipo de usuario con la información proporcionada")
    public ResponseEntity<TypeUserDto> createTypeUser(@RequestBody TypeUserDto typeUser){
        TypeUserDto createdTypeUserDto= typeUserService.createTypeUser(typeUser);
        return new ResponseEntity<>(createdTypeUserDto, HttpStatus.CREATED);
    }

}
