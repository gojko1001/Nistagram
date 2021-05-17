package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.CreateCommentDto;
import com.xws.nistagrammonolith.domain.Comment;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICommentService {
    List<Comment> getAllByPost(Long id);
    Comment create(Comment comment);
    CreateCommentDto createCommentOnPost(CreateCommentDto createCommentDto);
}
