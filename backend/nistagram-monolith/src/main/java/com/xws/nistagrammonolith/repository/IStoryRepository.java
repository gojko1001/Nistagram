package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.media.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoryRepository extends JpaRepository<Story, Long> {
    Story save (Story story);
}
