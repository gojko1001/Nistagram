package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {
    Like save(Like like);
    List<Like> findAll();
    List<Like> findLikesByUsername(String username);
}
