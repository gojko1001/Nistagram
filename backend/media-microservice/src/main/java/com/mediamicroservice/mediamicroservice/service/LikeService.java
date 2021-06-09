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
        if (post == null) {
            log.error("Post with id " + createLikeDto.getPostId() + " doesn't exist.");
            return null;
        }
        Date currentDate = new Date();
        Like newLike = new Like();
        if (post.getLikes().size() > 0) {
            for (Like like : post.getLikes()) {
                if (like.getUsername().equals(createLikeDto.getUsername())) {
                    if (createLikeDto.isLiked() == like.isLiked()) {
                        post.getLikes().remove(like);
                        postService.save(post);
                        log.info("Removed like with id  " + like.getId() + " from post with id " + post.getId());
                        likeRepository.delete(like);
                        log.info("Removed like with id  " + like.getId());
                        return null;
                    } else if (createLikeDto.isLiked() != like.isLiked()) {
                        like.setLiked(createLikeDto.isLiked());
                        like.setTimestamp(currentDate);
                        likeRepository.save(like);
                        return null;
                    }
                }
            }
        }

        newLike.setLiked(createLikeDto.isLiked());
        newLike.setUsername(createLikeDto.getUsername());
        newLike.setTimestamp(currentDate);
        log.info("Try to save like: " + newLike.getId());
        likeRepository.save(newLike);
        log.info("Like: " + newLike.getId() + " has been saved.");
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
    public boolean hasBeenLiked(Long id, String username) {
        Post post = postService.getById(id);
        for (Like like : post.getLikes()) {
            if (like.getUsername().equals(username))
                return true;
        }
        return false;
    }

    //TODO: mozda da se vraca bas slika a ne naziv
    @Override
    public List<Like> history(String username) {
        log.info("Try to find likes by username: " + username);
        return likeRepository.findLikesByUsername(username);
    }
}