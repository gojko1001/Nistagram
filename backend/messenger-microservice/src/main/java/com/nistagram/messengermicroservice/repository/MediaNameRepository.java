package com.nistagram.messengermicroservice.repository;

import com.nistagram.messengermicroservice.domain.MediaName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaNameRepository extends JpaRepository<MediaName, Long> {
}
