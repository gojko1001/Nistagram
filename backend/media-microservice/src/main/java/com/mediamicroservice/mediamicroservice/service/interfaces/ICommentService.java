package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateCommentDto;
import com.mediamicroservice.mediamicroservice.domain.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> getAllByPost(Long id);

    Comment createCommentOnPost(CreateCommentDto createCommentDto);
}
