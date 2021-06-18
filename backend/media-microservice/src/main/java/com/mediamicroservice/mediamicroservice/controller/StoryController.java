package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.domain.Story;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.repository.IStoryRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/story")
public class StoryController {
    @Autowired
    private IStoryService storyService;
    @Autowired
    private IStoryRepository storyRepository;

    private static String uploadDir = "user-photos";

    @PostMapping("/info")
    public ResponseEntity saveImageInfo(@RequestBody MediaDto mediaDto) {
        Logger.info("Save image info.", mediaDto.getUsername());
        return storyService.saveImageInfo(mediaDto);
    }

    @GetMapping("/archive/{username}")
    public ResponseEntity getArchivedStoriesByUsername(@PathVariable("username") String username) {
        Logger.info("Get archived stories.", username);
        List<Story> userPosts = storyRepository.findStoryByMedia_Username(username);
        return new ResponseEntity(storyService.getImagesFiles(userPosts), HttpStatus.OK);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity getStoriesByUsername(@PathVariable("username") String username) {
        Logger.info("Get stories.", username);
        List<Story> userStories = storyRepository.findStoryByMedia_Username(username);
        return new ResponseEntity(storyService.validStories(storyService.getImagesFiles(userStories)), HttpStatus.OK);
    }

    @GetMapping("/highlights/{username}")
    public ResponseEntity getHighlightedStoriesByUsername(@PathVariable("username") String username) {
        Logger.info("Get highlights.", username);
        List<Story> userStories = storyRepository.findStoryByMedia_Username(username);
        return new ResponseEntity(storyService.highlightedStories(storyService.getImagesFiles(userStories)), HttpStatus.OK);
    }
}
