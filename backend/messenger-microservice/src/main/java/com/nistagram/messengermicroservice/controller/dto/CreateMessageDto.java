package com.nistagram.messengermicroservice.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateMessageDto {
    private String content;
    private String sender;
    private String receiver;
    private List<String> fileNames;
}
