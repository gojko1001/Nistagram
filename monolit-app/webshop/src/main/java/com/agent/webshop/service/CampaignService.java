package com.agent.webshop.service;

import com.agent.webshop.connection.CampaignApiConnection;
import com.agent.webshop.controller.dto.CampaignDto;
import com.agent.webshop.security.JwtService;
import com.agent.webshop.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CampaignService implements ICampaignService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private CampaignApiConnection campaignApiConnection;

    @Override
    public void createCampaign(CampaignDto campaignDto) {
        campaignApiConnection.createCampaign(campaignDto, generateApiToken());
    }

    @Override
    public void uploadImages(List<MultipartFile> multipartFiles) {
        campaignApiConnection.uploadImages(multipartFiles, generateApiToken());
    }

    private String generateApiToken(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return jwtService.createApiToken(username);
    }
}
