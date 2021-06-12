package com.mediamicroservice.mediamicroservice.service;


import com.mediamicroservice.mediamicroservice.controller.dto.CollectionDto;
import com.mediamicroservice.mediamicroservice.controller.dto.ImageBytesDto;
import com.mediamicroservice.mediamicroservice.domain.Collection;
import com.mediamicroservice.mediamicroservice.domain.Favourite;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.repository.ICollectionRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.ICollectionService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionService implements ICollectionService {
    @Autowired
    private ICollectionRepository collectionRepository;
    @Autowired
    private IPostService postService;

    @Override
    public Collection save(Collection collection) {
        Logger.infoDb("Try to save collection: " + collection.getName());
        return collectionRepository.save(collection);
    }

    @Override
    public Collection findByName(String name) {
        Logger.infoDb("Read " + name + "collection.");
        return collectionRepository.findCollectionByName(name);
    }

    private List<Collection> findByUsername(String username) {
        Logger.infoDb("Find collections by username: " + username);
        return collectionRepository.findCollectionsByUsername(username);
    }

    @Override
    public ResponseEntity createCollection(Collection collection) {
        if (collectionRepository.findCollectionByNameAndUsername(collection.getName(), collection.getUsername()) != null)
            return new ResponseEntity("Collection with that name already exist.", HttpStatus.OK);
        return new ResponseEntity(save(collection), HttpStatus.OK);
    }

    @Override
    public ResponseEntity findCollectionsByUsername(String username) {
        if (findByUsername(username) == null) {
            return new ResponseEntity("User doesn't have collection.", HttpStatus.OK);
        }
        List<CollectionDto> resultCollections = new ArrayList<>();
        List<Collection> collections = findByUsername(username);
        for (Collection coll : collections) {
            List<ImageBytesDto> imageBytesDtos = new ArrayList<>();
            for (Favourite fav : coll.getFavourites()) {
                imageBytesDtos.add(postService.getImageFile(fav.getPost()));
            }
            CollectionDto collectionDto = new CollectionDto();
            collectionDto.setId(coll.getId());
            collectionDto.setName(coll.getName());
            collectionDto.setUsername(coll.getUsername());
            collectionDto.setFavourites(imageBytesDtos);
            resultCollections.add(collectionDto);
        }
        return new ResponseEntity(resultCollections, HttpStatus.OK);
    }

}
