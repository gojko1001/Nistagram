package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.Image;
import com.xws.nistagrammonolith.repository.IImageRepository;
import com.xws.nistagrammonolith.service.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Override
    public List<Image> getAll() {
        return null;
    }

    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }
}
