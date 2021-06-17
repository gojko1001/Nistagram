package com.mediamicroservice.mediamicroservice.controller.dto;

import com.mediamicroservice.mediamicroservice.domain.Album;
import lombok.Data;

import java.util.List;

@Data
public class MediaDto {
    private String username;
    private String fileName;
    private String description;
    private String locationName;
    private List<String> tags;
    private Album album;
}
