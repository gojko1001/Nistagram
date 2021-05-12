package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.ImageDto;
import com.xws.nistagrammonolith.domain.Image;
import com.xws.nistagrammonolith.service.FileUploadUtil;
import com.xws.nistagrammonolith.service.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "${clientURL}")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @PostMapping
    public String saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", ""));       //TODO: slucaj sa istim nazivima
        String uploadDir = "user-photos";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return fileName;
    }

    @PostMapping("/info")
    public Image saveImageInfo(@RequestBody ImageDto imageDto){
        Image image = new Image();
        image.setFileName(imageDto.getFileName());
        image.setUsername(imageDto.getUsername());
        image.setDescription(imageDto.getDescription());
        image.setLocation(imageDto.getLocation());
        image.setTags(imageDto.getTags());
        return imageService.create(image);
    }

}
