package com.nistagram.campaignmicroservice.controller;

import com.nistagram.campaignmicroservice.controller.dto.CampaignDto;
import com.nistagram.campaignmicroservice.domain.enums.CampaignType;
import com.nistagram.campaignmicroservice.repository.ICampaignRepository;
import com.nistagram.campaignmicroservice.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign")
public class CampaignController {
    @Autowired
    private ICampaignService campaignService;
    @Autowired
    private ICampaignRepository campaignRepository;

    @PostMapping
    public void createCampaign(@RequestBody CampaignDto campaignDto){
        campaignService.createCampaign(campaignDto);
    }

    @GetMapping("/post/{username}")
    public List<Long> getPostCampaignAds(@PathVariable String username){
        return campaignService.getAdvertisements(username, CampaignType.POST);
    }

    @GetMapping("/story/{username}")
    public List<Long> getStoryCampaignAds(@PathVariable String username){
        return campaignService.getAdvertisements(username, CampaignType.STORY);
    }
}
