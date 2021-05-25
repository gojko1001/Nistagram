package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.CreateLikeDto;
import com.xws.nistagrammonolith.domain.Like;
import com.xws.nistagrammonolith.domain.Post;
import com.xws.nistagrammonolith.repository.ILikeRepository;
import com.xws.nistagrammonolith.service.interfaces.ILikeService;
import com.xws.nistagrammonolith.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;
    @Autowired
    private IPostService postService;

    @Override
    public Like createLikeOnPost(CreateLikeDto createLikeDto) {
        Post post = postService.getById(createLikeDto.getPostId());
        for (Like like : post.getLikes()) {
            if (like.getUsername().equals(createLikeDto.getUsername())) {
                post.getLikes().remove(like);
                postService.save(post);
                likeRepository.delete(like);
                return null;
            }
        }
        Like newLike = new Like();
        newLike.setUsername(createLikeDto.getUsername());
        likeRepository.save(newLike);
        post.getLikes().add(newLike);
        postService.save(post);
        return newLike;
    }

    //TODO: za sada je nekoriscena na frontu
    @Override
    public Integer countLikesOnPost(Long id) {
        Post post = postService.getById(id);
        /*if(post == null)
            return new ResponseEntity("Post doesn't exist.", HttpStatus.BAD_REQUEST);*/
        return post.getLikes().size();
    }

    @Override
    public boolean hasBeenLiked(Long id, String username) {
        Post post = postService.getById(id);
        for (Like like : post.getLikes()) {
            if (like.getUsername().equals(username))
                return true;
        }
        return false;
    }
}
