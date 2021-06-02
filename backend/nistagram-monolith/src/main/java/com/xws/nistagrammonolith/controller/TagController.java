package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.domain.Hashtag;
import com.xws.nistagrammonolith.service.interfaces.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "${clientURL}")
public class TagController {

    @Autowired
    private ITagService tagService;

    @GetMapping
    public List<Hashtag> getAll() {
        return tagService.getAll();
    }

    @PostMapping
    public Hashtag create(@RequestBody Hashtag tag) {
        return tagService.create(tag);
    }
}
