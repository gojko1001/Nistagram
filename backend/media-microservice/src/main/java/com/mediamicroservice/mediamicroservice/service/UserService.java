package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.*;
import com.mediamicroservice.mediamicroservice.repository.*;
import com.mediamicroservice.mediamicroservice.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private ICollectionRepository collectionRepository;
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IInappropriateContentRepository inappropriateContentRepository;
    @Autowired
    private ILikeRepository likeRepository;
    @Autowired
    private IMediaRepository mediaRepository;
    @Autowired
    private IUserTagRepository userTagRepository;


    @Override
    public void changeUsername(String newUsername, String oldUsername) {
        for(Collection coll : collectionRepository.findCollectionsByUsername(oldUsername)){
            coll.setUsername(newUsername);
            collectionRepository.save(coll);
        }
        for(Comment comm : commentRepository.findCommentsByUsername(oldUsername)){
            comm.setUsername(newUsername);
            commentRepository.save(comm);
        }
        for(InappropriateContent content : inappropriateContentRepository.findInappropriateContentsByRequestedBy(oldUsername)){
            content.setRequestedBy(newUsername);
            inappropriateContentRepository.save(content);
        }
        for(InappropriateContent content : inappropriateContentRepository.findInappropriateContentsByRespondedBy(oldUsername)){
            content.setRequestedBy(newUsername);
            inappropriateContentRepository.save(content);
        }
        for(Like like : likeRepository.findLikesByUsername(oldUsername)){
            like.setUsername(newUsername);
            likeRepository.save(like);
        }
        for(Media media : mediaRepository.findMediaByUsername(oldUsername)){
            media.setUsername(newUsername);
            mediaRepository.save(media);
        }
        for(UserTag tag : userTagRepository.findUserTagsByUsername(oldUsername)){
            tag.setUsername(newUsername);
            userTagRepository.save(tag);
        }
    }
}
