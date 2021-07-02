package com.nistagram.campaignmicroservice.service;

import com.nistagram.campaignmicroservice.repository.ICampaignRepository;
import com.nistagram.campaignmicroservice.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService implements ICampaignService {
    @Autowired
    private ICampaignRepository campaignRepository;

}
