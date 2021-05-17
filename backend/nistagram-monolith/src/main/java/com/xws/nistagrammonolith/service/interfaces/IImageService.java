package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.controller.dto.ImageDto;
import com.xws.nistagrammonolith.domain.Image;

import java.util.List;

public interface IImageService {
    List<Image> getAll();
    Image create(Image image);
    List<Image> getUserImages(String username);
    Image saveImageInfo(ImageDto imageDto);
    List<ImageBytesDto> intoImageBytesDto(List<Image> images);
    Image getById(Long id);
}
