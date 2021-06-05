package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    Comment save(Comment comment);

    List<Comment> findAll();
}
