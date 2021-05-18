package com.xws.nistagrammonolith.controller.dto;

import com.xws.nistagrammonolith.domain.Location;
import lombok.Data;

import java.util.List;

@Data
public class ImageDto {
    private String username;
    private String fileName;
    private String description;
    private String locationName;
    private List<String> tags;
}