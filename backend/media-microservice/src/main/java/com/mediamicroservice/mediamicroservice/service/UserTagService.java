package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.connection.UserConnection;
import com.mediamicroservice.mediamicroservice.controller.dto.UserDto;
import com.mediamicroservice.mediamicroservice.domain.UserTag;
import com.mediamicroservice.mediamicroservice.repository.IUserTagRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IUserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTagService implements IUserTagService {
    @Autowired
    private IUserTagRepository userTagRepository;
    @Autowired
    private UserConnection userConnection;

    @Override
    public UserTag save(UserTag userTag) {
        return userTagRepository.save(userTag);
    }

    @Override
    public List<String> getUserTagsByUsername(String username) {
        List<String> publicUsernames = userConnection.getPublicTaggableUsers();
        List<UserDto> followingUsernames = userConnection.getFollowings(username);
        List<String> userTags = new ArrayList<>();
        if (publicUsernames != null) {
            for (String pu : publicUsernames) {
                userTags.add(pu);
            }
        }
        if (followingUsernames != null) {
            for (UserDto fu : followingUsernames) {
                if (!userTags.contains(fu.getUsername()) && fu.isTaggable()) {
                    userTags.add(fu.getUsername());
                }
            }
        }

        if (userTags.contains(username)) {
            userTags.remove(username);
        }

        return userTags;
    }
}
