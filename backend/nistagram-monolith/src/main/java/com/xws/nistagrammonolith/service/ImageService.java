package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.controller.dto.ImageDto;
import com.xws.nistagrammonolith.domain.Image;
import com.xws.nistagrammonolith.domain.Location;
import com.xws.nistagrammonolith.repository.IImageRepository;
import com.xws.nistagrammonolith.service.interfaces.IImageService;
import com.xws.nistagrammonolith.service.interfaces.ILocationService;
import com.xws.nistagrammonolith.service.interfaces.ITagService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository imageRepository;
    @Autowired
    private ILocationService locationService;
    @Autowired
    private ITagService tagService;

    private static String uploadDir = "user-photos";

    @Override
    public List<Image> getAll() {
        return null;
    }

    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public List<Image> getUserImages(String username){ return imageRepository.findImagesByUsername(username);}

    @Override
    public Image saveImageInfo(ImageDto imageDto){
        Image image = new Image();
        image.setFileName(imageDto.getFileName());
        image.setUsername(imageDto.getUsername());
        image.setDescription(imageDto.getDescription());
        Location location = locationService.findByName(imageDto.getLocationName());
        image.setLocation(location);
        image.setTags(tagService.createTags(imageDto.getTags()));
        return create(image);
    }

    @Override
    public List<ImageBytesDto> intoImageBytesDto(List<Image> images){
        List<ImageBytesDto> imageBytesDtos = new ArrayList<>();
        if(images != null){
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/" + uploadDir + "/");
            for(Image image: images){
                ImageBytesDto temp = new ImageBytesDto();
                temp.setId(image.getId());
                temp.setUsername(image.getUsername());
                temp.setDescription(image.getDescription());
                temp.setLocation(image.getLocation());
                temp.setTags(image.getTags());
                temp.setImageBytes(new ArrayList<>());
                File in = new File(filePath + image.getFileName());
                try {
                    temp.getImageBytes().add(IOUtils.toByteArray(new FileInputStream(in)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageBytesDtos.add(temp);
            }
        }
        return imageBytesDtos;
    }

}
