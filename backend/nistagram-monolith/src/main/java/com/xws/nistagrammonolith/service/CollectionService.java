package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.CollectionDto;
import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.domain.media.Collection;
import com.xws.nistagrammonolith.domain.media.Favourite;
import com.xws.nistagrammonolith.repository.ICollectionRepository;
import com.xws.nistagrammonolith.service.interfaces.ICollectionService;
import com.xws.nistagrammonolith.service.interfaces.IPostService;
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
    public Collection save(Collection collection){
        return collectionRepository.save(collection);
    }

    @Override
    public Collection findByName(String name){
        return collectionRepository.findCollectionByName(name);
    }

    @Override
    public ResponseEntity createCollection(Collection collection){
        if(collectionRepository.findCollectionByNameAndUsername(collection.getName(), collection.getUsername()) != null)
            return new ResponseEntity("Collection with that name already exist.", HttpStatus.OK);
        return new ResponseEntity(save(collection), HttpStatus.OK);
    }

    @Override
    public ResponseEntity findCollectionsByUsername(String username){
        if(collectionRepository.findCollectionsByUsername(username) == null){
            return new ResponseEntity("User doesn't have collection.", HttpStatus.OK);
        }
        List<CollectionDto> resultCollections = new ArrayList<>();
        List<Collection> collections = collectionRepository.findCollectionsByUsername(username);
        for(Collection coll: collections){
            List<ImageBytesDto> imageBytesDtos =  new ArrayList<>();
            for(Favourite fav: coll.getFavourites()){
                imageBytesDtos.add(postService.getImageFile(fav.getPost()));
            }
            CollectionDto collectionDto = new CollectionDto();
            collectionDto.setId(coll.getId());
            collectionDto.setName(coll.getName());
            collectionDto.setUsername(coll.getUsername());
            collectionDto.setFavourites(imageBytesDtos);
            resultCollections.add(collectionDto);
        }
        return  new ResponseEntity(resultCollections, HttpStatus.OK);
    }

}
