package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.Tag;

import java.util.List;

public interface ITagService {
    List<Tag> getAll();
    Tag create(Tag tag);
    List<Tag> createTags(List<String> tags);
}
