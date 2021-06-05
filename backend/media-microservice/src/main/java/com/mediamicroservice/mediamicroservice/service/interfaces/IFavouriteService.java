package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateFavouriteDto;
import com.mediamicroservice.mediamicroservice.domain.Favourite;
import org.springframework.http.ResponseEntity;

public interface IFavouriteService {
    Favourite create(Favourite favourite);
    ResponseEntity addFavouriteToCollection(CreateFavouriteDto createFavouriteDto);
}
