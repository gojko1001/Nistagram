package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.domain.Hashtag;

import java.util.List;

public interface IHashtagService {

    List<Hashtag> getAll();

    List<Hashtag> getHashtagsByUsername(String username);

    Hashtag create(Hashtag hashtag);

    List<Hashtag> createTags(List<String> tags);
}
