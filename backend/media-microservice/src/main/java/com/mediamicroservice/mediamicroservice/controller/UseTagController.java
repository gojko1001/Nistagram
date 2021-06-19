package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.service.interfaces.IUserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usertag")
@CrossOrigin(origins = "https://localhost:3000")
public class UseTagController {
    @Autowired
    private IUserTagService userTagService;

    @GetMapping("/{username}")
    public List<String> getUserTagByUsername(@PathVariable("username") String username) {
        Logger.info("Get user tags by username.", username);
        return userTagService.getUserTagsByUsername(username);
    }
}
