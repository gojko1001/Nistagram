package com.mediamicroservice.mediamicroservice.controller.dto;

import lombok.Data;

@Data
public class ReportConfirmationDto {
    private Long id;
    private String confirmedBy;
    private String confirmed;
}
