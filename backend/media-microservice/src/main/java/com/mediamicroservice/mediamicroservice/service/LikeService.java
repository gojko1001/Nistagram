package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateLikeDto;
import com.mediamicroservice.mediamicroservice.domain.Like;
import com.mediamicroservice.mediamicroservice.domain.Post;
import com.mediamicroservice.mediamicroservice.repository.ILikeRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.ILikeService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class LikeService implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;
    @Autowired
    private IPostService postService;

    //  todo: ispraviti
    @Override
    public Like createLikeOnPost(CreateLikeDto createLikeDto) {
        Post post = postService.getById(createLikeDto.getPostId());
        Date currentDate = new Date();
        System.out.println(currentDate);
        /*for(Like like: post.getLikes()){
            if(like.getUsername().equals(createLikeDto.getUsername())){
                post.getLikes().remove(like);
                postService.save(post);
                Like oldLike = new Like();
                oldLike.setUsername(createLikeDto.getUsername());
                oldLike.setLiked(false);
                oldLike.setTimestamp(currentDate);
                oldLike.setPost(post);
                log.info("Try to save like: " + oldLike.getId());
                return likeRepository.save(oldLike);
            }
        }*/
        Like newLike = new Like();
        newLike.setUsername(createLikeDto.getUsername());
        newLike.setLiked(true);
        newLike.setTimestamp(currentDate);
        //newLike.setPost(post);
        log.info("Try to save like: " + newLike.getId());
        likeRepository.save(newLike);
        post.getLikes().add(newLike);
        postService.save(post);
        return newLike;
    }

    //TODO: za sada je nekoriscena na frontu
    @Override
    public Integer countLikesOnPost(Long id) {
        log.info("Try to get post likes: " + id);
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
        log.info("Try to find likes by username: " + username);
        return likeRepository.findLikesByUsername(username);
    }
}