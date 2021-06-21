package com.mediamicroservice.mediamicroservice.controller.dto;

import com.mediamicroservice.mediamicroservice.domain.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ImageBytesDto {
    private Long id;
    private String username;
    private String description;
    private Location location;
    private Date timestamp;
    private List<Hashtag> hashtags;
    private List<Comment> comments;
    private List<Like> likes;
    private List<ImageByte> imageBytes;
    private Long mediaId;
    private List<UserTag> userTags;
}
