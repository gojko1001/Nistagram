package com.xws.nistagrammonolith.controller.mapping;


import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.domain.Post;

import java.util.ArrayList;

public class PostMapper {
    public static ImageBytesDto mapImageToImageBytesDto(Post post) {
        ImageBytesDto imageBytesDto = new ImageBytesDto();
        imageBytesDto.setId(post.getId());
        imageBytesDto.setUsername(post.getUsername());
        imageBytesDto.setDescription(post.getDescription());
        imageBytesDto.setLocation(post.getLocation());
        imageBytesDto.setHashtags(post.getTags());
        imageBytesDto.setComments(post.getComments());
        imageBytesDto.setImage(post.isImage());
        imageBytesDto.setImageBytes(new ArrayList<>());
        imageBytesDto.setLikes(post.getLikes());
        return imageBytesDto;
    }
}
