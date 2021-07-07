package com.nistagram.messengermicroservice.controller.dto;

import lombok.Data;

@Data
public class ImageByteDto {
    private byte[] imageByte;
    private boolean isImage;
}
