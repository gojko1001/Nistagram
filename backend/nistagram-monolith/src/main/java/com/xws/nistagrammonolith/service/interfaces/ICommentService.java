package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getAll();
    Comment create(Comment comment);
}
