package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.CreateCommentDto;
import com.xws.nistagrammonolith.domain.Comment;
import com.xws.nistagrammonolith.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "${clientURL}")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @PostMapping
    public Comment createCommentOnPost(@RequestBody CreateCommentDto createCommentDto) {
        return commentService.createCommentOnPost(createCommentDto);
    }
}
