package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Long> {
    Location save(Location location);
    List<Location> findAll();
    Location findByName(String name);
}