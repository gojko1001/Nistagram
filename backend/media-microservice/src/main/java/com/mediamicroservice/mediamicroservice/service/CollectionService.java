package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.Collection;
import com.mediamicroservice.mediamicroservice.repository.ICollectionRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CollectionService implements ICollectionService {
    @Autowired
    private ICollectionRepository collectionRepository;

    @Override
    public Collection save(Collection collection){
        return collectionRepository.save(collection);
    }

    @Override
    public ResponseEntity createCollection(Collection collection){
        if(collectionRepository.findCollectionByNameAndUsername(collection.getName(), collection.getUsername()) != null)
            return new ResponseEntity("Collection with that name already exist.", HttpStatus.OK);
        return new ResponseEntity(save(collection), HttpStatus.OK);
    }

    @Override
    public ResponseEntity findCollectionsByUsername(String username){
        if(collectionRepository.findCollectionsByUsername(username) == null)
            return new ResponseEntity("User doesn't have collection.", HttpStatus.OK);
        else
            return  new ResponseEntity(collectionRepository.findCollectionsByUsername(username), HttpStatus.OK);
    }
}
