package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITagRepository extends JpaRepository<Hashtag, Long> {

    Hashtag save(Hashtag tag);

    List<Hashtag> findAll();

    Hashtag findByName(String name);
}
