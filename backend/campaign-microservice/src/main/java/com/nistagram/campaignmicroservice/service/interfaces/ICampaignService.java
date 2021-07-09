package com.nistagram.campaignmicroservice.service.interfaces;

import com.nistagram.campaignmicroservice.controller.dto.CampaignDto;
import com.nistagram.campaignmicroservice.domain.Campaign;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICampaignService {

    Campaign findById(Long id);

    void createCampaign(CampaignDto campaignDto);

    List<String> uploadImages(List<MultipartFile> multipartFiles);
}
