package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.domain.Story;
import com.mediamicroservice.mediamicroservice.repository.IStoryRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IStoryService;
import com.mediamicroservice.mediamicroservice.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/story")
public class StoryController {
    @Autowired
    private IStoryService storyService;
    @Autowired
    private IStoryRepository storyRepository;

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

    @GetMapping("/archive/{username}")
    public ResponseEntity getArchivedStoriesByUsername(@PathVariable("username") String username) {
        List<Story> userPosts = storyRepository.findStoryByMedia_Username(username);
        return new ResponseEntity(storyService.getImagesFiles(userPosts), HttpStatus.OK);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity getStoriesByUsername(@PathVariable("username") String username) {
        List<Story> userPosts = storyRepository.findStoryByMedia_Username(username);
        return new ResponseEntity(storyService.validStories(storyService.getImagesFiles(userPosts)), HttpStatus.OK);
    }
}
