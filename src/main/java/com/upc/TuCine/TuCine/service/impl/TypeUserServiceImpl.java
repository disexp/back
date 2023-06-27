package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Ticket;
import com.upc.TuCine.TuCine.model.TypeUser;
import com.upc.TuCine.TuCine.repository.TypeUserRepository;
import com.upc.TuCine.TuCine.service.TypeUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeUserServiceImpl implements TypeUserService {

    @Autowired
    private TypeUserRepository typeUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TypeUserServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    private TypeUserDto EntityToDto(TypeUser typeUser){
        return modelMapper.map(typeUser, TypeUserDto.class);
    }

    private TypeUser DtoToEntity(TypeUserDto typeUserDto){
        return modelMapper.map(typeUserDto, TypeUser.class);
    }
    @Override
    public List<TypeUserDto> getAllTypeUsers() {
        List<TypeUser> typeUsers = typeUserRepository.findAll();
        return typeUsers.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeUserDto createTypeUser(TypeUserDto typeUserDto) {
        validateTypeUser(typeUserDto);
        existsTypeUserByName(typeUserDto.getName());
        TypeUser typeUser = DtoToEntity(typeUserDto);
        TypeUser createdTypeUser = typeUserRepository.save(typeUser);
        return EntityToDto(createdTypeUser);
    }
    void validateTypeUser(TypeUserDto typeUser) {
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
