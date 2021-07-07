package com.nistagram.messengermicroservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MessageDto {
    private Long id;
    private String content;
    private String sender;
    private String receiver;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;
    private Boolean viewed;
    private List<ImageByteDto> imageBytes;
    private Boolean hasMedia;
    private boolean privateLink;
    private String link;
    private Boolean needRequest;
}
