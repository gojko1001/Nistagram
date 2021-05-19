package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.CreateLikeDto;
import com.xws.nistagrammonolith.domain.Like;
import org.springframework.http.ResponseEntity;

public interface ILikeService {
    Like createLikeOnPost(CreateLikeDto createLikeDto);
    Integer countLikesOnPost(Long id);
    boolean hasBeenLiked(Long id, String username);
}
