package com.xws.nistagrammonolith.controller.dto;

import lombok.Data;

@Data
public class CreateFavouriteDto {
    private Long postId;
    private String collectionName;
}
