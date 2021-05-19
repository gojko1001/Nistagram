package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.CreateLikeDto;
import com.xws.nistagrammonolith.domain.Like;
import com.xws.nistagrammonolith.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
