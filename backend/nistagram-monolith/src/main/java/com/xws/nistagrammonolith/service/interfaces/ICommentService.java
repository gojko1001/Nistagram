package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.CreateCommentDto;
import com.xws.nistagrammonolith.domain.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> getAllByPost(Long id);

    Comment createCommentOnPost(CreateCommentDto createCommentDto);
}
