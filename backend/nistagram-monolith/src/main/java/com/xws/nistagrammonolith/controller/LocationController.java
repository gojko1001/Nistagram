package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.domain.media.Location;
import com.xws.nistagrammonolith.service.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "${clientURL}")
public class LocationController {
    @Autowired
    private ILocationService locationService;

    @GetMapping
    public List<Location> getAll() {
        return locationService.getAll();
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        return locationService.create(location);
    }
}
