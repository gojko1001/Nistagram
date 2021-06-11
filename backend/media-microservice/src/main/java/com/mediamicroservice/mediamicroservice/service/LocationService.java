package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.Location;
import com.mediamicroservice.mediamicroservice.repository.ILocationRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.ILocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LocationService implements ILocationService {
    @Autowired
    private ILocationRepository locationRepository;

    @Override
    public List<Location> getAll() {
        List<Location> locations = locationRepository.findAll();
        if (locations.isEmpty())
            log.info("There is no any location");
        return locations;
    }

    @Override
    public Location create(Location location) {
        log.info("Try to save location: " + location.getName());
        return locationRepository.save(location);
    }

    @Override
    public Location findById(Long id) {
        log.info("Try to find location with id: " + id);
        return locationRepository.findLocationById(id);
    }

    @Override
    public Location findByName(String name) {
        log.info("Try to find location with name: " + name);
        return locationRepository.findLocationByName(name);
    }
}
