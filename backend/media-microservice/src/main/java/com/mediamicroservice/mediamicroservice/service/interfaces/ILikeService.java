package com.mediamicroservice.mediamicroservice.service.interfaces;


import com.mediamicroservice.mediamicroservice.controller.dto.CreateLikeDto;
import com.mediamicroservice.mediamicroservice.controller.dto.ImageBytesDto;
import com.mediamicroservice.mediamicroservice.domain.Like;

import java.util.List;

public interface ILikeService {

    Like createLikeOnPost(CreateLikeDto createLikeDto);

    Integer countLikesOnPost(Long id);

    boolean hasBeenLiked(Long id, String username);

    List<ImageBytesDto> history(String username);
}
