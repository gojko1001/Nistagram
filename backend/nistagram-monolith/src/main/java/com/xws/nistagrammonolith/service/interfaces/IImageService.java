package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.Image;

import java.util.List;

public interface IImageService {
    List<Image> getAll();
    Image create(Image image);
    List<Image> getUserImages(String username);
}
