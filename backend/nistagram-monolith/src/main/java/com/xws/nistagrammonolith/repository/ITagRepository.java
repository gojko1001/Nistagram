package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.Tag;
import com.xws.nistagrammonolith.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long> {
    Tag save(Tag tag);
    List<Tag> findAll();
    Tag findByName(String name);
}
