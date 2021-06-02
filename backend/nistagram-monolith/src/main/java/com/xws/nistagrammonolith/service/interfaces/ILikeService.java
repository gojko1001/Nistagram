package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.CreateLikeDto;
import com.xws.nistagrammonolith.domain.media.Like;

import java.util.List;

public interface ILikeService {

    Like createLikeOnPost(CreateLikeDto createLikeDto);

    Integer countLikesOnPost(Long id);

    boolean hasBeenLiked(Long id, String username);
    List<Like> history(String username);
}
