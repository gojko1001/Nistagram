package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.Image;
import com.xws.nistagrammonolith.service.interfaces.IImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {
    @Override
    public List<Image> getAll() {
        return null;
    }

    @Override
    public Image create(Image image) {
        return null;
    }
}
