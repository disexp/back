package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.OwnerDto;

import java.util.List;

public interface OwnerService {

    List<OwnerDto> getAllOwners();

    OwnerDto createOwner(OwnerDto ownerDto);
}
