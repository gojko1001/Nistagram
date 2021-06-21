package com.nistagram.notificationmicroservice.domain;

import lombok.Data;

@Data
public class ImageByte {
    private byte[] imageByte;
    private boolean isImage;
}
