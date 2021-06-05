package com.mediamicroservice.mediamicroservice.controller.dto;

import lombok.Data;

@Data
public class CreateFavouriteDto {
    private Long postId;
    private String collectionName;
}
