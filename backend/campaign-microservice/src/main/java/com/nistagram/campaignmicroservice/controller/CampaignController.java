package com.nistagram.campaignmicroservice.controller;

import com.nistagram.campaignmicroservice.controller.dto.CampaignDto;
import com.nistagram.campaignmicroservice.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaign")
public class CampaignController {
    @Autowired
    private ICampaignService campaignService;

    @PostMapping
    public void createCampaign(@RequestBody CampaignDto campaignDto){
        campaignService.createCampaign(campaignDto);
    }
}
