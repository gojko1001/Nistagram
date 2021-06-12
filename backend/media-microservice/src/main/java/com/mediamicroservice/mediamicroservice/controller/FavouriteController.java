package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateFavouriteDto;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.service.interfaces.IFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {
    @Autowired
    private IFavouriteService favouriteService;

    @PostMapping
    public ResponseEntity addFavouriteToCollection(@RequestBody CreateFavouriteDto createFavouriteDto) {
        ResponseEntity res = favouriteService.addFavouriteToCollection(createFavouriteDto);
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
