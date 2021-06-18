package com.mediamicroservice.mediamicroservice.controller.dto;

import com.mediamicroservice.mediamicroservice.domain.MediaName;
import lombok.Data;

import java.util.List;

@Data
public class MediaDto {
    private String username;
    private List<String> fileNames;
    private String description;
    private String locationName;
    private List<String> tags;
    private MediaName mediaName;
    private List<String> userTags;
}
