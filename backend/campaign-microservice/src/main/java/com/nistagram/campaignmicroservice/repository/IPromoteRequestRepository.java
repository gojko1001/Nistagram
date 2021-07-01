package com.nistagram.campaignmicroservice.repository;

import com.nistagram.campaignmicroservice.domain.PromoteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromoteRequestRepository extends JpaRepository<PromoteRequest, Long> {
}
