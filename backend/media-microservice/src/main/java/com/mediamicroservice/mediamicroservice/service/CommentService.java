package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateCommentDto;
import com.mediamicroservice.mediamicroservice.domain.Comment;
import com.mediamicroservice.mediamicroservice.domain.Post;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.repository.ICommentRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.ICommentService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IPostService imageService;

    @Override
    public List<Comment> getAllByPost(Long id) {
        return null;
    }

    @Override
    public Comment createCommentOnPost(CreateCommentDto createCommentDto) {
        Comment comment = new Comment();
        comment.setUsername(createCommentDto.getUsername());
        comment.setText(createCommentDto.getText());
        comment.setTimestamp(new Date());
        Logger.infoDb("Try to save comment: " + comment.getId());
        commentRepository.save(comment);
        Post post = imageService.getById(createCommentDto.getPostId());
        List<Comment> comments = post.getComments();
        comments.add(comment);
        imageService.save(post);
        return comment;
    }

}
