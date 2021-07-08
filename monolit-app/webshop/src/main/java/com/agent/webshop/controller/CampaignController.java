package com.agent.webshop.controller;

import com.agent.webshop.controller.dto.CampaignDto;
import com.agent.webshop.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaign")
public class CampaignController {
    @Autowired
    private ICampaignService campaignService;

    @PreAuthorize("hasAuthority('CREATE_CAMPAIGN')")
    @PostMapping
    public void createCampaign(@RequestBody CampaignDto campaignDto){
        campaignService.createCampaign(campaignDto);
    }
}
