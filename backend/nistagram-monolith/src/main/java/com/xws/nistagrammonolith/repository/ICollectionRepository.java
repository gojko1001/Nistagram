package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.media.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICollectionRepository extends JpaRepository<Collection, Long> {
    Collection save(Collection collection);
    List<Collection> findCollectionsByUsername(String username);
    Collection findCollectionByNameAndUsername(String name, String username);
    Collection findCollectionByName(String name);
}
