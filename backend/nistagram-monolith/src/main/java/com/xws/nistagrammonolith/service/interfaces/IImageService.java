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
    List<ImageBytesDto> getImagesFiles(List<Image> images);
    ImageBytesDto imageFile(Image image, String filePath);
    Image getById(Long id);
    ImageBytesDto getImageFileById(Long id);
}
