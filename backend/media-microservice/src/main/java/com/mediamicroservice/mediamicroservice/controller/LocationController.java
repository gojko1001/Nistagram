package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.domain.Location;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.service.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private ILocationService locationService;

    @GetMapping
    public List<Location> getAll() {
        Logger.info("Get all locations", "");
        return locationService.getAll();
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        Logger.info("Create location.", "");
        return locationService.create(location);
    }
}
