package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.ImageDto;
import com.xws.nistagrammonolith.domain.Image;
import com.xws.nistagrammonolith.repository.IImageRepository;
import com.xws.nistagrammonolith.service.FileUploadUtil;
import com.xws.nistagrammonolith.service.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private IImageRepository imageRepository;
    @Autowired
    private IImageService imageService;

    private static String uploadDir = "user-photos";

    @PostMapping
    public String saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", ""));       //TODO: slucaj sa istim nazivima
        uploadDir = "user-photos";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return fileName;
    }


    @PostMapping("/info")
    public Image saveImageInfo(@RequestBody ImageDto imageDto){
        return imageService.saveImageInfo(imageDto);
    }


    @GetMapping("/profile/{username}")
    public ResponseEntity getImagesByUsername(@PathVariable("username") String username){
        List<Image> userImages = imageRepository.findImagesByUsername(username);
        return new ResponseEntity(imageService.intoImageBytesDto(userImages), HttpStatus.OK);
    }


    @GetMapping("/discover/{username}")
    public ResponseEntity getDiscoverImages(@PathVariable("username") String username){
        List<Image> discoverImages = imageRepository.findAll();
        // TODO: provera da l je profil public i da l se prate ili je u pitanju gost
        // TODO: clean code
        return new ResponseEntity(imageService.intoImageBytesDto(discoverImages), HttpStatus.OK);
    }

}
