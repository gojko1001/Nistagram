package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.Hashtag;

import java.util.List;

public interface ITagService {

    List<Hashtag> getAll();

    Hashtag create(Hashtag tag);

    List<Hashtag> createTags(List<String> tags);
}
