package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.MediaDto;
import com.xws.nistagrammonolith.domain.media.Story;
import com.xws.nistagrammonolith.service.interfaces.IStoryService;
import com.xws.nistagrammonolith.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/story")
@CrossOrigin(origins = "${clientURL}")
public class StoryController {
    @Autowired
    private IStoryService storyService;

    private static String uploadDir = "user-photos";

    @PostMapping
    public String saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", ""));       //TODO: slucaj sa istim nazivima
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return fileName;
    }

    @PostMapping("/info")
    public Story saveImageInfo(@RequestBody MediaDto imageDto) {
        return storyService.saveImageInfo(imageDto);
    }
}
