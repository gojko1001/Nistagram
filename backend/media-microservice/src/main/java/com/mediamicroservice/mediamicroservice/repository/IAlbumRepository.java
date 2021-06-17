package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlbumRepository extends JpaRepository<Album, Long> {
    Album save(Album album);
}
