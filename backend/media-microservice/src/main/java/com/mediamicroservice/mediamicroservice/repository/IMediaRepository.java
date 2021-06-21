package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMediaRepository extends JpaRepository<Media, Long> {

    Media findMediaById(Long id);

    List<Media> findMediaByUsername(String username);
}
