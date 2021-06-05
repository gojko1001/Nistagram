package com.mediamicroservice.mediamicroservice.controller.dto;

import com.mediamicroservice.mediamicroservice.domain.Comment;
import com.mediamicroservice.mediamicroservice.domain.Hashtag;
import com.mediamicroservice.mediamicroservice.domain.Like;
import com.mediamicroservice.mediamicroservice.domain.Location;
import lombok.Data;

import java.util.List;

@Data
public class ImageBytesDto {
    private Long id;
    private String username;
    private String description;
    private Location location;
    private List<Hashtag> hashtags;
    private List<Comment> comments;
    private boolean isImage;
    private List<Like> likes;
    private List<byte[]> imageBytes;
}
