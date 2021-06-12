package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.domain.Collection;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.service.interfaces.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private ICollectionService collectionService;

    @PostMapping
    public ResponseEntity createCollection(@RequestBody Collection collection) {
        Logger.info("Create collection.", collection.getUsername());
        return collectionService.createCollection(collection);
    }

    @GetMapping("/{username}")
    public ResponseEntity findCollectionsByUsername(@PathVariable("username") String username) {
        Logger.info("Find collections.", username);
        return collectionService.findCollectionsByUsername(username);
    }
}
