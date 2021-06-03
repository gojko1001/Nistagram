package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.media.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHashtagRepository extends JpaRepository<Hashtag, Long> {

    Hashtag save(Hashtag hashtag);

    List<Hashtag> findAll();

    Hashtag findByName(String name);
}
