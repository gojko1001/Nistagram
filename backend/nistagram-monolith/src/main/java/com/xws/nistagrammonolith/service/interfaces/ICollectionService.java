package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.media.Collection;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICollectionService {
    Collection save(Collection collection);
    ResponseEntity createCollection (Collection collection);
    ResponseEntity findCollectionsByUsername(String username);
}
