package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.CategoryDto;
import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.*;
import com.upc.TuCine.TuCine.repository.GenderRepository;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import com.upc.TuCine.TuCine.repository.TypeUserRepository;
import com.upc.TuCine.TuCine.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private TypeUserRepository typeUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    PersonServiceImpl(){
        this.modelMapper = new ModelMapper();

    }

    private PersonDto EntityToDto(Person person){
        return modelMapper.map(person, PersonDto.class);
    }

    private Person DtoToEntity(PersonDto personDto){
        return modelMapper.map(personDto, Person.class);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<Person> persons= personRepository.findAll();
        return persons.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeUserDto getTypeUserByPersonId(Integer id) {
       Person person = personRepository.findById(id).orElse(null);
         if (person == null) {
              return null;
         }
         TypeUserDto typeUserDto = modelMapper.map(person.getTypeUser(), TypeUserDto.class);
            return typeUserDto;
    }
    @Override
    public PersonDto createPerson(PersonDto personDto) {

        validatePerson(personDto);

        Gender gender = genderRepository.findById(personDto.getGender().getId()).orElse(null);
        personDto.setGender(gender);

        TypeUser typeUser = typeUserRepository.findById(personDto.getTypeUser().getId()).orElse(null);
        personDto.setTypeUser(typeUser);

        Person person = DtoToEntity(personDto);
        return EntityToDto(personRepository.save(person));
    }

    private void validatePerson(PersonDto personDto){

        if (personDto.getFirstName() == null || personDto.getFirstName().isEmpty()){
            throw new ValidationException("El nombre es obligatorio");
        }
        if (personDto.getLastName() == null || personDto.getLastName().isEmpty()){
            throw new ValidationException("El apellido es obligatorio");
        }
        if (personDto.getNumberDni() == null || personDto.getNumberDni().isEmpty()){
            throw new ValidationException("El número de DNI es obligatorio");
        }
        if (personDto.getEmail() == null || personDto.getEmail().isEmpty()){
            throw new ValidationException("El correo es obligatorio");
        }
        if (personDto.getPassword() == null || personDto.getPassword().isEmpty()){
            throw new ValidationException("La contraseña es obligatoria");
        }
        if (personDto.getGender() == null){
            throw new ValidationException("El género es obligatorio");
        }
        if (personDto.getTypeUser() == null){
            throw new ValidationException("El tipo de usuario es obligatorio");
        }
    }

    @Override
    public boolean existsByPersonEmail(String email) {
        return personRepository.existsPersonByEmail(email);
    }

    @Override
    public boolean existsPersonByNumberDni(String numberDni) {
        return personRepository.existsPersonByNumberDni(numberDni);
    }
}
