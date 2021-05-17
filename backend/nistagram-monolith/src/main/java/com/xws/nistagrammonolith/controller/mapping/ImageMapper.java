package com.xws.nistagrammonolith.controller.mapping;


import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.domain.Image;

import java.util.ArrayList;

public class ImageMapper {
    public static ImageBytesDto mapImageToImageBytesDto(Image image){
        ImageBytesDto imageBytesDto = new ImageBytesDto();
        imageBytesDto.setId(image.getId());
        imageBytesDto.setUsername(image.getUsername());
        imageBytesDto.setDescription(image.getDescription());
        imageBytesDto.setLocation(image.getLocation());
        imageBytesDto.setTags(image.getTags());
        imageBytesDto.setComments(image.getComments());
        imageBytesDto.setImageBytes(new ArrayList<>());
        return imageBytesDto;
    }
}
