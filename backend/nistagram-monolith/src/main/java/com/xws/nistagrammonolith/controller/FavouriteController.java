package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.CreateFavouriteDto;
import com.xws.nistagrammonolith.service.interfaces.IFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favourite")
@CrossOrigin(origins = "${clientURL}")
public class FavouriteController {
    @Autowired
    private IFavouriteService favouriteService;

    @PostMapping
    public ResponseEntity addFavouriteToCollection(@RequestBody CreateFavouriteDto createFavouriteDto) {
        ResponseEntity res = favouriteService.addFavouriteToCollection(createFavouriteDto);
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
