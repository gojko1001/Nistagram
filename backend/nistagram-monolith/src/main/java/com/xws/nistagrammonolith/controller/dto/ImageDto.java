package com.xws.nistagrammonolith.controller.dto;

import com.xws.nistagrammonolith.domain.Location;
import com.xws.nistagrammonolith.domain.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ImageDto {
    private String username;
    private String fileName;
    private String description;
    private Location location;
    private List<String> tags;
}
