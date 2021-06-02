package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.CreateCommentDto;
import com.xws.nistagrammonolith.domain.Comment;
import com.xws.nistagrammonolith.repository.ICommentRepository;
import com.xws.nistagrammonolith.service.interfaces.ICommentService;
import com.xws.nistagrammonolith.service.interfaces.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IPostService imageService;
    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Override
    public Comment createCommentOnPost(CreateCommentDto createCommentDto) {
        ModelMapper modelMapper = new ModelMapper();
        Object result = (Object) rabbitTemplate.convertSendAndReceive("createcomment.queue", createCommentDto);
        return modelMapper.map(result, Comment.class);
    }

}
