package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.ActorDto;
import com.upc.TuCine.TuCine.model.Actor;

import java.util.List;

public interface ActorService {
    ActorDto createActor(ActorDto actorDto);

    List<ActorDto> getAllActors();

}
