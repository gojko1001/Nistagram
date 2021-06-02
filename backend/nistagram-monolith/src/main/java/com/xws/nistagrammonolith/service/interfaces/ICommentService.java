package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.CreateCommentDto;
import com.xws.nistagrammonolith.domain.Comment;

import java.util.List;

public interface ICommentService {
    Comment createCommentOnPost(CreateCommentDto createCommentDto);
}
