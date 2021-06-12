package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.domain.Hashtag;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.service.interfaces.IHashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private IHashtagService tagService;

    @GetMapping
    public List<Hashtag> getAll() {
        Logger.info("Get alll tags.", "");
        return tagService.getAll();
    }

    @PostMapping
    public Hashtag create(@RequestBody Hashtag tag) {
        Logger.info("Create tag: " + tag.getName(), "");
        return tagService.create(tag);
    }
}
