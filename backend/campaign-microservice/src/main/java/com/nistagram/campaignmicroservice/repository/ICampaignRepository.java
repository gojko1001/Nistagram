package com.nistagram.campaignmicroservice.repository;

import com.nistagram.campaignmicroservice.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICampaignRepository extends JpaRepository<Campaign, Long> {
}
