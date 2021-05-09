package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.Comment;
import com.xws.nistagrammonolith.service.interfaces.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public Comment create(Comment comment) {
        return null;
    }
}
