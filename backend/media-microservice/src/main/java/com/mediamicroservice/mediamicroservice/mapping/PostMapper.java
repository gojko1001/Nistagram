package com.mediamicroservice.mediamicroservice.mapping;


import com.mediamicroservice.mediamicroservice.domain.Post;
import com.mediamicroservice.mediamicroservice.dto.ImageBytesDto;

import java.util.ArrayList;

public class PostMapper {
    public static ImageBytesDto mapImageToImageBytesDto(Post post) {
        ImageBytesDto imageBytesDto = new ImageBytesDto();
        imageBytesDto.setId(post.getId());
        imageBytesDto.setComments(post.getComments());
        imageBytesDto.setImageBytes(new ArrayList<>());
        imageBytesDto.setLikes(post.getLikes());
        return imageBytesDto;
    }
}
