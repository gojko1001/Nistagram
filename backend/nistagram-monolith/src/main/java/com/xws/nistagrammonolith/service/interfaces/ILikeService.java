package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.CreateLikeDto;
import com.xws.nistagrammonolith.domain.Like;

public interface ILikeService {
    Like createLikeOnPost(CreateLikeDto createLikeDto);
}
