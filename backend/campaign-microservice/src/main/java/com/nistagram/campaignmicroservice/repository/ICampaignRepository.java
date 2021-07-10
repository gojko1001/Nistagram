package com.nistagram.campaignmicroservice.repository;

import com.nistagram.campaignmicroservice.domain.Campaign;
import com.nistagram.campaignmicroservice.domain.enums.CampaignType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICampaignRepository extends JpaRepository<Campaign, Long> {

//    @Query(value = "select distinct c from Campaign c where c.type = (:campType) and" +
//            "((c.type = 0 and current_date between c.startDate and c.endDate) or " +
//            "(c.type = 1 and c.startDate = current_date))")
    List<Campaign> findCampaignsByType(CampaignType campType);
}
