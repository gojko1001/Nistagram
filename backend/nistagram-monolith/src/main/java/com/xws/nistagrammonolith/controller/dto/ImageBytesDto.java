package com.xws.nistagrammonolith.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageBytesDto {
    private Long id;
    private List<byte[]> imageBytes;
}
