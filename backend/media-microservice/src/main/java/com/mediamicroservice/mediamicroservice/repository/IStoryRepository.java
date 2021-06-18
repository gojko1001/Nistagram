package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStoryRepository extends JpaRepository<Story, Long> {
    Story save (Story story);
    List<Story> findStoryByMedia_Username(String username);
    Story findStoryById(Long id);
}
