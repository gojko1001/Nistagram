package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.Location;

import java.util.List;

public interface ILocationService {
    List<Location> getAll();
    Location create(Location location);
}
