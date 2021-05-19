package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);
    List<Post> findAll();
    List<Post> findPostsByUsername(String username);
    Post findPostById(Long id);
}
