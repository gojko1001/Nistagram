package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);
    List<Post> findAll();
    List<Post> findPostsByMedia_Username(String username);
    Post findPostById(Long id);
    @Query(value = "select p from Post p where lower(p.media.location.name) like concat('%', lower(:location), '%' )")
    List<Post> searchLocation(String location);
    Post findPostByMedia_Id(Long id);
}
