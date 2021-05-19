package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.CreateLikeDto;
import com.xws.nistagrammonolith.domain.Like;
import com.xws.nistagrammonolith.domain.Post;
import com.xws.nistagrammonolith.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@CrossOrigin(origins = "${clientURL}")
public class LikeController {
    @Autowired
    private ILikeService likeService;

    @PostMapping
    public Like createLikeOnPost(@RequestBody CreateLikeDto createLikeDto){
        return likeService.createLikeOnPost(createLikeDto);
    }

    @GetMapping("/{id}")
    public Integer getImagesByUsername(@PathVariable("id") Long id){
        return likeService.countLikesOnPost(id);
    }

    @GetMapping("/post/{id}/{username}")
    public boolean hasBeenLiked(@PathVariable("id") Long id, @PathVariable("username")String username){
        return likeService.hasBeenLiked(id, username);
    }

}
