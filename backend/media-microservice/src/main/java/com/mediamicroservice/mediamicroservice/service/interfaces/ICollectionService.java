package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.domain.Collection;
import org.springframework.http.ResponseEntity;

public interface ICollectionService {
    Collection save(Collection collection);
    ResponseEntity createCollection (Collection collection);
    ResponseEntity findCollectionsByUsername(String username);
    Collection findByName(String name);
}
