package com.nistagram.campaignmicroservice.service.interfaces;

import com.nistagram.campaignmicroservice.controller.dto.CampaignDto;
import com.nistagram.campaignmicroservice.domain.Campaign;

public interface ICampaignService {

    Campaign findById(Long id);

    void createCampaign(CampaignDto campaignDto);
}
