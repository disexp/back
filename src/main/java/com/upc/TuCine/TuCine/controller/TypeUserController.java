package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.TypeUser;
import com.upc.TuCine.TuCine.repository.TypeUserRepository;
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
    private TypeUserRepository typeUserRepository;

    public TypeUserController(TypeUserRepository typeUserRepository) {
        this.typeUserRepository = typeUserRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/typeUsers
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/typeUsers")
    @Operation(summary = "Obtener todos los TypeUsers", description = "Obtiene la lista de todos los tipos de usuarios disponibles")
    public ResponseEntity<List<TypeUser>> getAllTypeUsers() {
        return new ResponseEntity<List<TypeUser>>(typeUserRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/typeUsers
    //Method: POST
    @Transactional
    @PostMapping("/typeUsers")
    @Operation(summary = "Crear un nuevo TypeUser", description = "Crea un nuevo tipo de usuario con la información proporcionada")
    public ResponseEntity<TypeUser> createTypeUser(@RequestBody TypeUser typeUser){
        validateTypeUser(typeUser);
        existsTypeUserByName(typeUser.getName());
        return new ResponseEntity<TypeUser>(typeUserRepository.save(typeUser), HttpStatus.CREATED);
    }

    void validateTypeUser(TypeUser typeUser) {
        if (typeUser.getName() == null || typeUser.getName().isEmpty()) {
            throw new ValidationException("El nombre del tipo de usuario no puede estar vacío");
        }
    }

    void existsTypeUserByName(String name) {
        if (typeUserRepository.existsTypeUserByName(name)) {
            throw new ValidationException("El tipo de usuario ya existe con este nombre");
        }
    }
}
