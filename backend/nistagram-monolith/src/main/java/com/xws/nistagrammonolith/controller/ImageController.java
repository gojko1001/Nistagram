package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.ImageDto;
import com.xws.nistagrammonolith.domain.Image;
import com.xws.nistagrammonolith.domain.Location;
import com.xws.nistagrammonolith.domain.Tag;
import com.xws.nistagrammonolith.service.FileUploadUtil;
import com.xws.nistagrammonolith.service.interfaces.IImageService;
import com.xws.nistagrammonolith.service.interfaces.ILocationService;
import com.xws.nistagrammonolith.service.interfaces.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "${clientURL}")
public class ImageController {

    @Autowired
    private IImageService imageService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ILocationService locationService;

    @PostMapping
    public String saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", ""));       //TODO: slucaj sa istim nazivima
        String uploadDir = "user-photos";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return fileName;
    }

    //TODO: prebaciti u ImageService
    @PostMapping("/info")
    public Image saveImageInfo(@RequestBody ImageDto imageDto){
        Image image = new Image();
        image.setFileName(imageDto.getFileName());
        image.setUsername(imageDto.getUsername());
        image.setDescription(imageDto.getDescription());
        Location location = locationService.findByName(imageDto.getLocationName());
        image.setLocation(location);
        image.setTags(tagService.createTags(imageDto.getTags()));
        return imageService.create(image);
    }

}
