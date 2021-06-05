package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);
    List<Post> findAll();
    List<Post> findPostsByMedia_Username(String username);
    Post findPostById(Long id);
}
