package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserTagRepository extends JpaRepository<UserTag, Long> {

    List<UserTag> findUserTagsByUsername(String username);
}
