package com.agent.webshop.service.interfaces;

import com.agent.webshop.controller.dto.CampaignDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICampaignService {

    void createCampaign(CampaignDto campaignDto);

    void uploadImages(List<MultipartFile> multipartFiles);
}
