package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.ActorDto;
import com.upc.TuCine.TuCine.model.Actor;
import com.upc.TuCine.TuCine.repository.ActorRepository;
import com.upc.TuCine.TuCine.service.ActorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ActorServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private ActorDto EntityToDto(Actor actor){
        return modelMapper.map(actor, ActorDto.class);
    }

    private Actor DtoToEntity(ActorDto actorDto){
        return modelMapper.map(actorDto, Actor.class);
    }

    @Override
    public ActorDto createActor(ActorDto actorDto) {
        Actor actor = DtoToEntity(actorDto);
        return EntityToDto(actorRepository.save(actor));
    }

    @Override
    public List<ActorDto> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        List<ActorDto> actorDtos = new ArrayList<>();
        for (Actor actor : actors) {
            actorDtos.add(EntityToDto(actor));
        }
        return actorDtos;
    }


    @Override
    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        return actorRepository.existsByFirstNameAndLastName(firstName, lastName);
    }


}
