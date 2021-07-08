package com.agent.webshop.service;

import com.agent.webshop.controller.dto.CampaignDto;
import com.agent.webshop.domain.User;
import com.agent.webshop.security.JwtService;
import com.agent.webshop.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CampaignService implements ICampaignService {

    @Autowired
    private JwtService jwtService;

    @Override
    public void createCampaign(CampaignDto campaignDto) {
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getDetails()).getUsername();
        String api_token = jwtService.createApiToken(username);

        //TODO: Call api endpoint from nistagram's campaign microservice
    }
}
