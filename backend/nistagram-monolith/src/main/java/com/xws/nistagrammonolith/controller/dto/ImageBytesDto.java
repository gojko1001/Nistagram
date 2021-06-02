package com.xws.nistagrammonolith.controller.dto;

import com.xws.nistagrammonolith.domain.media.*;
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
