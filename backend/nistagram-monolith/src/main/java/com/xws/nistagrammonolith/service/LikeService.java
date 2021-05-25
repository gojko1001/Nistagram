package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.CreateLikeDto;
import com.xws.nistagrammonolith.domain.Like;
import com.xws.nistagrammonolith.domain.Post;
import com.xws.nistagrammonolith.exception.NotFoundException;
import com.xws.nistagrammonolith.repository.ILikeRepository;
import com.xws.nistagrammonolith.repository.IPostRepository;
import com.xws.nistagrammonolith.service.interfaces.IPostService;
import com.xws.nistagrammonolith.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LikeService implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;
    @Autowired
    private IPostService postService;

    @Override
    public Like createLikeOnPost(CreateLikeDto createLikeDto) {
        Post post = postService.getById(createLikeDto.getPostId());
        Date currentDate = new Date();
        System.out.println(currentDate);
        for(Like like: post.getLikes()){
            if(like.getUsername().equals(createLikeDto.getUsername())){
                post.getLikes().remove(like);
                postService.save(post);
                Like oldLike = new Like();
                oldLike.setUsername(createLikeDto.getUsername());
                oldLike.setLiked(false);
                oldLike.setTimestamp(currentDate);
                oldLike.setPost(post);
                return likeRepository.save(oldLike);
            }
        }
        Like newLike = new Like();
        newLike.setUsername(createLikeDto.getUsername());
        newLike.setLiked(true);
        newLike.setTimestamp(currentDate);
        newLike.setPost(post);
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
    public boolean hasBeenLiked(Long id, String username){
        Post post = postService.getById(id);
        for(Like like: post.getLikes()){
            if(like.getUsername().equals(username))
                return true;
        }
        return false;
    }

    //TODO: mozda da se vraca bas slika a ne naziv
    @Override
    public List<Like> history(String username){
        return likeRepository.findLikesByUsername(username);
    }
}