package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.media.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMediaRepository extends JpaRepository<Media, Long> {
}
