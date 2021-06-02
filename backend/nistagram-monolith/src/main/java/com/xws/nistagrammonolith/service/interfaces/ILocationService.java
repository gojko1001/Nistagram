package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.media.Location;

import java.util.List;

public interface ILocationService {

    List<Location> getAll();

    Location create(Location location);

    Location findById(Long id);

    Location findByName(String name);
}
