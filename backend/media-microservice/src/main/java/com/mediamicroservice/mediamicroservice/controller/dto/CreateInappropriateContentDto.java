package com.mediamicroservice.mediamicroservice.controller.dto;

import lombok.Data;

@Data
public class CreateInappropriateContentDto {
    private Long id;
    private String requestedBy;
    private Long mediaId;
}
