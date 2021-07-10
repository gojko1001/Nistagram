package com.agent.webshop.controller;

import com.agent.webshop.controller.dto.CampaignDto;
import com.agent.webshop.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @PreAuthorize("hasAuthority('UPLOAD_IMG')")
    @PostMapping("/upload_img")
    public void saveImage(@RequestParam("file") List<MultipartFile> multipartFiles){
        campaignService.uploadImages(multipartFiles);
    }
}
