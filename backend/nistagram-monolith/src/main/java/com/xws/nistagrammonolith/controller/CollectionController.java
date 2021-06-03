package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.domain.media.Collection;
import com.xws.nistagrammonolith.service.interfaces.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
@CrossOrigin(origins = "${clientURL}")
public class CollectionController {
    @Autowired
    private ICollectionService collectionService;

    @PostMapping
    public ResponseEntity createCollection(@RequestBody Collection collection) {
        return collectionService.createCollection(collection);
    }

    @GetMapping("/{username}")
    public ResponseEntity findCollectionsByUsername(@PathVariable("username") String username) {
        return collectionService.findCollectionsByUsername(username);
    }
}
