package com.nistagram.usermicroservice.user.repository;

import com.nistagram.usermicroservice.user.domain.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRelationRepository extends JpaRepository<UserRelation, Long> {
}
