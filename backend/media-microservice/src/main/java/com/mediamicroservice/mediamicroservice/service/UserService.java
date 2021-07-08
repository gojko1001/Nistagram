package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.*;
import com.mediamicroservice.mediamicroservice.repository.*;
import com.mediamicroservice.mediamicroservice.saga.event.GetUsernameEvent;
import com.mediamicroservice.mediamicroservice.saga.event.MessageFactory;
import com.mediamicroservice.mediamicroservice.saga.event.UpdateUsernameEvent;
import com.mediamicroservice.mediamicroservice.saga.eventListener.CancelUpdateListener;
import com.mediamicroservice.mediamicroservice.saga.eventListener.UsernameUpdateDone;
import com.mediamicroservice.mediamicroservice.service.interfaces.IUserService;
import com.mediamicroservice.mediamicroservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UsernameUpdateDone usernameUpdateDone;
    @Autowired
    private CancelUpdateListener cancelUpdateListener;


    @Override
    public void changeUsername(String newUsername, String oldUsername) {
        for (Collection coll : collectionRepository.findCollectionsByUsername(oldUsername)) {
            coll.setUsername(newUsername);
            collectionRepository.save(coll);
        }
        for (Comment comm : commentRepository.findCommentsByUsername(oldUsername)) {
            comm.setUsername(newUsername);
            commentRepository.save(comm);
        }
        for (InappropriateContent content : inappropriateContentRepository.findInappropriateContentsByRequestedBy(oldUsername)) {
            content.setRequestedBy(newUsername);
            inappropriateContentRepository.save(content);
        }
        for (InappropriateContent content : inappropriateContentRepository.findInappropriateContentsByRespondedBy(oldUsername)) {
            content.setRequestedBy(newUsername);
            inappropriateContentRepository.save(content);
        }
        for (Like like : likeRepository.findLikesByUsername(oldUsername)) {
            like.setUsername(newUsername);
            likeRepository.save(like);
        }
        for (Media media : mediaRepository.findMediaByUsername(oldUsername)) {
            media.setUsername(newUsername);
            mediaRepository.save(media);
        }
        for (UserTag tag : userTagRepository.findUserTagsByUsername(oldUsername)) {
            tag.setUsername(newUsername);
            userTagRepository.save(tag);
        }
    }

    @Override
    public void updateUsername(GetUsernameEvent event) {
        try{
            changeUsername(event.getNewUsername(), event.getOldUsername());
            usernameUpdateDone.sendMessageToNewUpdate(MessageFactory.createUpdateUsernameMessage(event.getNewUsername(), event.getOldUsername()));
        }catch (Exception e){
            cancelUpdateListener.sendMessageToNewUpdate(MessageFactory.createUpdateUsernameMessage(event.getNewUsername(), event.getOldUsername()));
        }

    }

    @Override
    public void cancelUpdateUsername(GetUsernameEvent event) {
        String oldUsername = event.getOldUsername();

        List<Collection> collectionList = collectionRepository.findCollectionsByUsername(oldUsername);
        collectionList.stream().forEach(collection -> {
            if (collection.getUsername().equals(event.getNewUsername())) {
                collection.setUsername(oldUsername);
                collectionRepository.save(collection);
            }
        });

        List<Comment> comments = commentRepository.findCommentsByUsername(oldUsername);
        comments.stream().forEach(comment -> {
            if (comment.getUsername().equals(event.getNewUsername())) {
                comment.setUsername(oldUsername);
                commentRepository.save(comment);
            }
        });

        List<InappropriateContent> inappropriateContents = inappropriateContentRepository.findInappropriateContentsByRequestedBy(oldUsername);
        inappropriateContents.stream().forEach(content -> {
            if (content.getRequestedBy().equals(event.getNewUsername())) {
                content.setRequestedBy(oldUsername);
                inappropriateContentRepository.save(content);
            }
        });

        List<InappropriateContent> contents = inappropriateContentRepository.findInappropriateContentsByRespondedBy(oldUsername);
        contents.stream().forEach(content -> {
            if (content.getRequestedBy().equals(event.getNewUsername())) {
                content.setRequestedBy(oldUsername);
                inappropriateContentRepository.save(content);
            }
        });

        List<Like> likes = likeRepository.findLikesByUsername(oldUsername);
        likes.stream().forEach(like -> {
            if (like.getUsername().equals(event.getNewUsername())) {
                like.setUsername(oldUsername);
                likeRepository.save(like);
            }
        });

        List<Media> mediaList = mediaRepository.findMediaByUsername(oldUsername);
        mediaList.stream().forEach(media -> {
            if (media.getUsername().equals(event.getNewUsername())) {
                media.setUsername(oldUsername);
                mediaRepository.save(media);
            }
        });

        List<UserTag> userTags = userTagRepository.findUserTagsByUsername(oldUsername);
        userTags.stream().forEach(tag -> {
            if (tag.getUsername().equals(event.getNewUsername())) {
                tag.setUsername(oldUsername);
                userTagRepository.save(tag);
            }
        });
    }
}
