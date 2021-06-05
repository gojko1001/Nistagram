package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.CreateFavouriteDto;
import com.xws.nistagrammonolith.domain.media.Favourite;
import org.springframework.http.ResponseEntity;

public interface IFavouriteService {
    Favourite create(Favourite favourite);
    ResponseEntity addFavouriteToCollection(CreateFavouriteDto createFavouriteDto);
}
