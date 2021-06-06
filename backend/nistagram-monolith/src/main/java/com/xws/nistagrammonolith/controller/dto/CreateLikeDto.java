package com.xws.nistagrammonolith.controller.dto;

import lombok.Data;

@Data
public class CreateLikeDto {
    private Long postId;
    private String username;
    private boolean liked;
}
