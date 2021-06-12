package com.mediamicroservice.mediamicroservice.service;


import com.mediamicroservice.mediamicroservice.controller.dto.CreateFavouriteDto;
import com.mediamicroservice.mediamicroservice.domain.Collection;
import com.mediamicroservice.mediamicroservice.domain.Favourite;
import com.mediamicroservice.mediamicroservice.domain.Post;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.repository.IFavouriteRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.ICollectionService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IFavouriteService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IPostService;
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
    public Favourite create(Favourite favourite) {
        Logger.infoDb("Try to save favourite: " + favourite.getId());
        return favouriteRepository.save(favourite);
    }

    @Override
    public ResponseEntity addFavouriteToCollection(CreateFavouriteDto createFavouriteDto) {
        Post post = postService.getById(createFavouriteDto.getPostId());
        Collection collection = collectionService.findByName(createFavouriteDto.getCollectionName());
        Logger.info("Add favorite to collection: " + collection.getName(), collection.getUsername());
        if (collection == null)
            return new ResponseEntity("Collection doesn't exist.", HttpStatus.OK);
        Favourite favourite = new Favourite();
        favourite.setPost(post);
        create(favourite);
        collection.getFavourites().add(favourite);
        collectionService.save(collection);
        return new ResponseEntity("Post has been added to collection.", HttpStatus.OK);
    }
}
