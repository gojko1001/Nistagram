package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.CreateLikeDto;
import com.xws.nistagrammonolith.domain.Like;
import com.xws.nistagrammonolith.domain.Post;
import com.xws.nistagrammonolith.exception.NotFoundException;
import com.xws.nistagrammonolith.repository.ILikeRepository;
import com.xws.nistagrammonolith.service.interfaces.IPostService;
import com.xws.nistagrammonolith.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;
    @Autowired
    private IPostService postService;

    @Override
    public Like createLikeOnPost(CreateLikeDto createLikeDto) {
        Like newLike = new Like();
        newLike.setUsername(createLikeDto.getUsername());
        likeRepository.save(newLike);
        Post post = postService.getById(createLikeDto.getPostId());
        List<Like> likes = post.getLikes();
        likes.add(newLike);
        postService.save(post);
        return null;
    }

    @Override
    public ResponseEntity countLikesOnPost(Long id) {
        Post post = postService.getById(id);
        if(post == null)
            return new ResponseEntity("Post doesn't exist.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(post.getLikes().size(), HttpStatus.OK);
    }
}
