package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.CreateCommentDto;
import com.xws.nistagrammonolith.domain.Comment;
import com.xws.nistagrammonolith.domain.Image;
import com.xws.nistagrammonolith.repository.ICommentRepository;
import com.xws.nistagrammonolith.service.interfaces.ICommentService;
import com.xws.nistagrammonolith.service.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IImageService imageService;

    @Override
    public List<Comment> getAllByPost(Long id) {
        return null;
    }

    @Override
    public Comment createCommentOnPost(CreateCommentDto createCommentDto) {
        Comment comment = new Comment();
        comment.setUsername(createCommentDto.getUsername());
        comment.setText(createCommentDto.getText());
        commentRepository.save(comment);
        Image image = imageService.getById(createCommentDto.getPostId());
        List<Comment> comments = image.getComments();
        comments.add(comment);
        imageService.create(image);
        return comment;
    }

}
