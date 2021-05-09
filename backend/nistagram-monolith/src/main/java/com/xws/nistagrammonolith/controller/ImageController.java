package com.xws.nistagrammonolith.controller;

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
    public boolean saveImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Image image = new Image();
        image.setFileName(fileName);
        image.setUsername("marijamasa");
        imageService.create(image);
        String uploadDir = "user-photos/bla";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return true;
    }
}
