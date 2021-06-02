package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.domain.Comment;
import com.mediamicroservice.mediamicroservice.dto.CreateCommentDto;

import java.util.List;

public interface ICommentService {
    Comment createCommentOnPost(CreateCommentDto createCommentDto);
}
