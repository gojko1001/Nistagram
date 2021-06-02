package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.Comment;
import com.mediamicroservice.mediamicroservice.dto.CreateCommentDto;
import com.mediamicroservice.mediamicroservice.repository.ICommentRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;
    /*@Autowired
    private IPostService imageService;*/

    @Override
    public Comment createCommentOnPost(CreateCommentDto createCommentDto) {
        Comment comment = new Comment();
        comment.setUsername(createCommentDto.getUsername());
        comment.setText(createCommentDto.getText());
        comment.setTimestamp(new Date());
        log.info("Try to save comment: " + comment.getId());
        commentRepository.save(comment);
        /*Post post = imageService.getById(createCommentDto.getPostId());
        List<Comment> comments = post.getComments();
        comments.add(comment);
        imageService.save(post);*/
        return comment;
    }

}
