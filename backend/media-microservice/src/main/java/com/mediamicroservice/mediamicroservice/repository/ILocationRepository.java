package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Long> {
}
