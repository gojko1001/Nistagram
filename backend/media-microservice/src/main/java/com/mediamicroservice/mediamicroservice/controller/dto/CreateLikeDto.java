package com.mediamicroservice.mediamicroservice.controller.dto;

import lombok.Data;

@Data
public class CreateLikeDto {
    private Long postId;
    private String username;
}
