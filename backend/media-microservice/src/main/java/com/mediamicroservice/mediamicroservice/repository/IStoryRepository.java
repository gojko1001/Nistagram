package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoryRepository extends JpaRepository<Story, Long> {
}
