package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.Hashtag;
import com.xws.nistagrammonolith.repository.ITagRepository;
import com.xws.nistagrammonolith.service.interfaces.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TagService implements ITagService {
    @Autowired
    private ITagRepository tagRepository;

    @Override
    public List<Hashtag> getAll() {
        List<Hashtag> tags = tagRepository.findAll();
        if(tags.isEmpty())
            log.info("There is no any tag.");
        return tags;
    }

    @Override
    public Hashtag create(Hashtag tag) {
        log.info("Try to save tag: " + tag.getName());
        return tagRepository.save(tag);
    }

    @Override
    public List<Hashtag> createTags(List<String> tags) {
        List<Hashtag> tagDb = new ArrayList<>();
        for (String t : tags) {
            if (tagRepository.findByName(t) == null) {
                Hashtag tag = new Hashtag();
                tag.setName(t.toLowerCase());
                create(tag);
                tagDb.add(tag);
            }
        }
        return tagDb;
    }

}
