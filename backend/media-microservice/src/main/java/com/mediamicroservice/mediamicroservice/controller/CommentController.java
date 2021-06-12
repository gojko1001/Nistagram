package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateCommentDto;
import com.mediamicroservice.mediamicroservice.domain.Comment;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @PostMapping
    public Comment createCommentOnPost(@RequestBody CreateCommentDto createCommentDto) {
        Logger.info("Create comment on post: " + createCommentDto.getPostId(), createCommentDto.getUsername());
        return commentService.createCommentOnPost(createCommentDto);
    }
}
