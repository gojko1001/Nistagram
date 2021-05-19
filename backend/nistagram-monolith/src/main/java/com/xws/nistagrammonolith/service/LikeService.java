package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.CreateLikeDto;
import com.xws.nistagrammonolith.domain.Like;
import com.xws.nistagrammonolith.domain.Post;
import com.xws.nistagrammonolith.repository.ILikeRepository;
import com.xws.nistagrammonolith.service.interfaces.IPostService;
import com.xws.nistagrammonolith.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;
    @Autowired
    private IPostService imageService;

    @Override
    public Like createLikeOnPost(CreateLikeDto createLikeDto) {
        Like newLike = new Like();
        newLike.setUsername(createLikeDto.getUsername());
        likeRepository.save(newLike);
        Post post = imageService.getById(createLikeDto.getPostId());
        List<Like> likes = post.getLikes();
        likes.add(newLike);
        imageService.save(post);
        return null;
    }
}
