package com.nistagram.campaignmicroservice.controller;

import com.nistagram.campaignmicroservice.JwtUtil;
import com.nistagram.campaignmicroservice.controller.dto.CampaignDto;
import com.nistagram.campaignmicroservice.exception.UnauthorizedException;
import com.nistagram.campaignmicroservice.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaign")
@CrossOrigin(origins = "http://localhost:4000")
public class WebShopApiController {

    @Autowired
    private ICampaignService campaignService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public void createCampaign(@RequestBody CampaignDto campaignDto,
                               @RequestHeader("x-api-key") String apiKey){
        String username = jwtUtil.extractUsername(apiKey);      // TODO: Check if user is agent
        if(username == null)
            throw new UnauthorizedException("Access denied");
        campaignDto.setUsername(username);
        campaignService.createCampaign(campaignDto);
    }
}
