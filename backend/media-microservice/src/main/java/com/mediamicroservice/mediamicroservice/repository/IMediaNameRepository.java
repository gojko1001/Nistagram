package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.MediaName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMediaNameRepository extends JpaRepository<MediaName, Long> {
    MediaName save(MediaName mediaName);

    MediaName findMediaNameById(Long id);
}
