package com.xws.nistagrammonolith.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class CollectionDto {
    private Long id;
    private String name;
    private List<ImageBytesDto> favourites;
    private String username;
}
