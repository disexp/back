package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.OwnerDto;
import com.upc.TuCine.TuCine.model.Gender;
import com.upc.TuCine.TuCine.model.Owner;
import com.upc.TuCine.TuCine.repository.OwnerRepository;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import com.upc.TuCine.TuCine.service.OwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    OwnerServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private OwnerDto EntityToDto(Owner owner){
        return modelMapper.map(owner, OwnerDto.class);
    }

    private Owner DtoToEntity(OwnerDto ownerDto){
        return modelMapper.map(ownerDto, Owner.class);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        List<Owner> owners= ownerRepository.findAll();
        return owners.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) {
        Owner owner = DtoToEntity(ownerDto);
        return EntityToDto(ownerRepository.save(owner));
    }

    @Override
    public boolean existsOwnerByAccountNumber(String accountNumber) {
        return ownerRepository.existsOwnerByBankAccount(accountNumber);
    }

    @Override
    public boolean existsPersonById(Integer id) {
        return personRepository.existsById(id);
    }
}
