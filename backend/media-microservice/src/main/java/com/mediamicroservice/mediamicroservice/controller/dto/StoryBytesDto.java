package com.mediamicroservice.mediamicroservice.controller.dto;

import com.mediamicroservice.mediamicroservice.domain.Hashtag;
import com.mediamicroservice.mediamicroservice.domain.Location;
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
    private Date timestamp;
    private List<ImageByte> imageBytes;
}
