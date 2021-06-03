package com.xws.nistagrammonolith.controller.dto;

import com.xws.nistagrammonolith.domain.media.Hashtag;
import com.xws.nistagrammonolith.domain.media.Location;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StoryBytesDto {
    private Long id;
    private String username;
    private String description;
    private Location location;
    private List<Hashtag> hashtags;
    private boolean isImage;
    private List<byte[]> imageBytes;
    private Date timestamp;
}
