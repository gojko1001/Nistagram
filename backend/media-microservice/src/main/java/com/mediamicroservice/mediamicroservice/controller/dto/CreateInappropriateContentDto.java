package com.mediamicroservice.mediamicroservice.controller.dto;

import lombok.Data;

@Data
public class CreateInappropriateContentDto {

    private String requestedBy;
    private Long mediaId;
}
