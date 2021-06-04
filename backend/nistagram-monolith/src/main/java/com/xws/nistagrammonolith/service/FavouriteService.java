package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.CreateFavouriteDto;
import com.xws.nistagrammonolith.domain.media.Collection;
import com.xws.nistagrammonolith.domain.media.Favourite;
import com.xws.nistagrammonolith.domain.media.Post;
import com.xws.nistagrammonolith.repository.IFavouriteRepository;
import com.xws.nistagrammonolith.service.interfaces.ICollectionService;
import com.xws.nistagrammonolith.service.interfaces.IFavouriteService;
import com.xws.nistagrammonolith.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FavouriteService implements IFavouriteService {
    @Autowired
    private IFavouriteRepository favouriteRepository;
    @Autowired
    private ICollectionService collectionService;
    @Autowired
    private IPostService postService;

    @Override
    public Favourite create(Favourite favourite){
        return favouriteRepository.save(favourite);
    }

    @Override
    public ResponseEntity addFavouriteToCollection(CreateFavouriteDto createFavouriteDto){
        Post post = postService.getById(createFavouriteDto.getPostId());
        Collection collection = collectionService.findByName(createFavouriteDto.getCollectionName());
        if(collection == null)
            return  new ResponseEntity("Collection doesn't exist.", HttpStatus.OK);
        Favourite favourite = new Favourite();
        favourite.setPost(post);
        favouriteRepository.save(favourite);
        collection.getFavourites().add(favourite);
        collectionService.save(collection);
        return new ResponseEntity("Post has been added to collection.", HttpStatus.OK);
    }
}
