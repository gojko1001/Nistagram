package com.nistagram.campaignmicroservice.repository;

import com.nistagram.campaignmicroservice.domain.TargetAudience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITargetAudienceRepository extends JpaRepository<TargetAudience, Long> {
}
