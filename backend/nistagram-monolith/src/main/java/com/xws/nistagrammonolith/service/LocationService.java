package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.Location;
import com.xws.nistagrammonolith.repository.ILocationRepository;
import com.xws.nistagrammonolith.service.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private ILocationRepository locationRepository;

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location create(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location findById(Long id) {
        return locationRepository.findLocationById(id);
    }

    @Override
    public Location findByName(String name){ return locationRepository.findLocationByName(name); }
}
