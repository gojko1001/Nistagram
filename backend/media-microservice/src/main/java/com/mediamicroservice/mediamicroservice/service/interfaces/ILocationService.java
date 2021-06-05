package com.mediamicroservice.mediamicroservice.service.interfaces;


import com.mediamicroservice.mediamicroservice.domain.Location;

import java.util.List;

public interface ILocationService {

    List<Location> getAll();

    Location create(Location location);

    Location findById(Long id);

    Location findByName(String name);
}
