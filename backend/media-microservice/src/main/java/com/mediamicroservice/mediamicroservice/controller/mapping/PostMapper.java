package com.mediamicroservice.mediamicroservice.controller.mapping;


import com.mediamicroservice.mediamicroservice.controller.dto.ImageBytesDto;
import com.mediamicroservice.mediamicroservice.domain.Post;

import java.util.ArrayList;

public class PostMapper {
    public static ImageBytesDto mapPostToImageBytesDto(Post post) {
        ImageBytesDto imageBytesDto = new ImageBytesDto();
        imageBytesDto.setId(post.getId());
        imageBytesDto.setUsername(post.getMedia().getUsername());
        imageBytesDto.setDescription(post.getMedia().getDescription());
        imageBytesDto.setLocation(post.getMedia().getLocation());
        imageBytesDto.setTimestamp(post.getMedia().getTimestamp());
        imageBytesDto.setHashtags(post.getMedia().getHashtags());
        imageBytesDto.setComments(post.getComments());
        imageBytesDto.setImage(post.getMedia().isImage());
        imageBytesDto.setImageBytes(new ArrayList<>());
        imageBytesDto.setLikes(post.getLikes());
        return imageBytesDto;
    }
}
