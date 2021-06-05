package com.mediamicroservice.mediamicroservice.controller.dto;

import lombok.Data;

@Data
public class CreateCommentDto {
    private Long postId;
    private String username;
    private String text;
}
