package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.CategoryDto;
import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.model.Film;
import com.upc.TuCine.TuCine.model.Owner;
import com.upc.TuCine.TuCine.model.Person;
import com.upc.TuCine.TuCine.repository.PersonRepository;
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
         TypeUserDto typeUserDto = modelMapper.map(person.getTypeUser_id(), TypeUserDto.class);
            return typeUserDto;
    }
    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = DtoToEntity(personDto);
        return EntityToDto(personRepository.save(person));
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
