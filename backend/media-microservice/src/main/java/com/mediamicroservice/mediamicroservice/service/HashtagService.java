package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.Hashtag;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.repository.IHashtagRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IHashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HashtagService implements IHashtagService {
    @Autowired
    private IHashtagRepository tagRepository;

    @Override
    public List<Hashtag> getAll() {
        List<Hashtag> tags = tagRepository.findAll();
        if (tags.isEmpty())
            Logger.infoDb("There is no any tag.");
        return tags;
    }

    @Override
    public Hashtag create(Hashtag tag) {
        Logger.infoDb("Try to save tag: " + tag.getName());
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
