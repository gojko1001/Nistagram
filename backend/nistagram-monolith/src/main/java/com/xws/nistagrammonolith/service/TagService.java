package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.Tag;
import com.xws.nistagrammonolith.repository.ITagRepository;
import com.xws.nistagrammonolith.service.interfaces.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class TagService implements ITagService {
    @Autowired
    private ITagRepository tagRepository;

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> createTags(List<String> tags){
        List<Tag> tagDb = new ArrayList<>();
        for(String t: tags){
            if(tagRepository.findByName(t) == null){
                Tag tag = new Tag();
                tag.setName(t.toLowerCase());
                tagRepository.save(tag);
                tagDb.add(tag);
            }
        }
        return tagDb;
    }

}
