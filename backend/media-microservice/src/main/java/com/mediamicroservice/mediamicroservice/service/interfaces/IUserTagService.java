package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.domain.UserTag;

import java.util.List;

public interface IUserTagService {
    UserTag save(UserTag userTag);

    List<String> getUserTagsByUsername(String username);
}
