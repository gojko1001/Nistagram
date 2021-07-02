package com.nistagram.campaignmicroservice.controller;

import com.nistagram.campaignmicroservice.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaign")
public class CampaignController {
    @Autowired
    private ICampaignService campaignService;

}
