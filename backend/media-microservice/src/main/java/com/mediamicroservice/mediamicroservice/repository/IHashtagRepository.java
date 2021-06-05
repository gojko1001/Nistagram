package com.mediamicroservice.mediamicroservice.repository;


import com.mediamicroservice.mediamicroservice.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IHashtagRepository extends JpaRepository<Hashtag, Long> {

    Hashtag save(Hashtag hashtag);

    List<Hashtag> findAll();

    Hashtag findByName(String name);
}
