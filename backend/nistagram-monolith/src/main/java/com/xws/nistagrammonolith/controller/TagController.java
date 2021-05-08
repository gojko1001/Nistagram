package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.domain.Tag;
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
    public List<Tag> getAll() {
        return tagService.getAll();
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return tagService.create(tag);
    }
}
