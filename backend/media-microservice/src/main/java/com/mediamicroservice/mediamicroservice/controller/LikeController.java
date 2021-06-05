package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateLikeDto;
import com.mediamicroservice.mediamicroservice.domain.Like;
import com.mediamicroservice.mediamicroservice.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private ILikeService likeService;

    @PostMapping
    public Like createLikeOnPost(@RequestBody CreateLikeDto createLikeDto) {
        return likeService.createLikeOnPost(createLikeDto);
    }

    @GetMapping("/{id}")
    public Integer getImagesByUsername(@PathVariable("id") Long id) {
        return likeService.countLikesOnPost(id);
    }

    @GetMapping("/post/{id}/{username}")
    public boolean hasBeenLiked(@PathVariable("id") Long id, @PathVariable("username") String username) {
        return likeService.hasBeenLiked(id, username);
    }

    @GetMapping("/history/{username}")
    public List<Like> history(@PathVariable("username")String username){
        return likeService.history(username);
    }


}
