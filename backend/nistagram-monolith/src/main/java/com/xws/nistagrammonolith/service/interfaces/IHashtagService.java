package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.media.Hashtag;

import java.util.List;

public interface IHashtagService {

    List<Hashtag> getAll();

    Hashtag create(Hashtag hashtag);

    List<Hashtag> createTags(List<String> tags);
}
