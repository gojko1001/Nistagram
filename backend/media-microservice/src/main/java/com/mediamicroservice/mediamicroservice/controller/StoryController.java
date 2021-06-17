package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.domain.Story;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.repository.IStoryRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/story")
public class StoryController {
    @Autowired
    private IStoryService storyService;
    @Autowired
    private IStoryRepository storyRepository;

    private static String uploadDir = "user-photos";

    /*@PostMapping("/info")
    public Story saveImageInfo(@RequestBody MediaDto imageDto) {
        Logger.info("Save image info: " + imageDto.getFileName(), imageDto.getUsername());
        return storyService.saveImageInfo(imageDto);
    }*/

    @GetMapping("/archive/{username}")
    public ResponseEntity getArchivedStoriesByUsername(@PathVariable("username") String username) {
        Logger.info("Get archived stories.", username);
        List<Story> userPosts = storyRepository.findStoryByMedia_Username(username);
        return new ResponseEntity(storyService.getImagesFiles(userPosts), HttpStatus.OK);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity getStoriesByUsername(@PathVariable("username") String username) {
        Logger.info("Get stories.", username);
        List<Story> userPosts = storyRepository.findStoryByMedia_Username(username);
        return new ResponseEntity(storyService.validStories(storyService.getImagesFiles(userPosts)), HttpStatus.OK);
    }
}
