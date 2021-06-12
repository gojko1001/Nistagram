package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.Location;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.repository.ILocationRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private ILocationRepository locationRepository;

    @Override
    public List<Location> getAll() {
        List<Location> locations = locationRepository.findAll();
        if (locations.isEmpty())
            Logger.infoDb("There is no any location");
        return locations;
    }

    @Override
    public Location create(Location location) {
        Location dbLocation = new Location();
        try {
            Logger.infoDb("Try to save location: " + location.getName());
            dbLocation = locationRepository.save(location);
        } catch (Exception e) {
            Logger.errorDb("Cannot save location: " + location.getName(), e.getMessage());
            e.printStackTrace();
        }
        return dbLocation;
    }

    @Override
    public Location findById(Long id) {
        Logger.infoDb("Try to find location with id: " + id);
        Location location = locationRepository.findLocationById(id);

        if (location == null) {
            Logger.infoDb("There is no location with id: " + id);
        }

        return location;
    }

    @Override
    public Location findByName(String name) {
        Logger.infoDb("Try to find location with name: " + name);
        Location location = locationRepository.findLocationByName(name);
        if (location == null)
            Logger.infoDb("There is no location with name: " + name);
        return location;
    }
}
