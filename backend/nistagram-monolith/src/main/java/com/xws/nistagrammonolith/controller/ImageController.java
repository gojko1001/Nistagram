package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.controller.dto.ImageDto;
import com.xws.nistagrammonolith.domain.Image;
import com.xws.nistagrammonolith.domain.Location;
import com.xws.nistagrammonolith.repository.IImageRepository;
import com.xws.nistagrammonolith.service.FileUploadUtil;
import com.xws.nistagrammonolith.service.interfaces.IImageService;
import com.xws.nistagrammonolith.service.interfaces.ILocationService;
import com.xws.nistagrammonolith.service.interfaces.ITagService;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "${clientURL}")
public class ImageController {

    @Autowired
    private IImageRepository imageRepository;
    @Autowired
    private IImageService imageService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ILocationService locationService;
    @Autowired
    private IUserService userService;

    private static String uploadDir = "user-photos";

    @PostMapping
    public String saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", ""));       //TODO: slucaj sa istim nazivima
        uploadDir = "user-photos";
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

    @GetMapping("/profile/{username}")
    public ResponseEntity getImagesByUsername(@PathVariable("username") String username){
        List<Image> userImages = imageRepository.findImagesByUsername(username);
        List<ImageBytesDto> imageBytesDtos = new ArrayList<>();
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/" + uploadDir + "/");
        for(Image image: userImages){
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
        return new ResponseEntity(imageBytesDtos, HttpStatus.OK);
    }

    @GetMapping("/discover/{username}")
    public ResponseEntity getDiscoverImages(@PathVariable("username") String username){
        List<Image> discoverImages = imageRepository.findAll();
        // TODO: provera da l je profil public i da l se prate ili je u pitanju gost
        // TODO: clean code
        List<ImageBytesDto> imageBytesDtos = new ArrayList<>();
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/" + uploadDir + "/");
        for(Image image: discoverImages){
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
        return new ResponseEntity(imageBytesDtos, HttpStatus.OK);
    }

}
