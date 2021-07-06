package com.nistagram.campaignmicroservice.service;

import com.nistagram.campaignmicroservice.connection.MediaConnection;
import com.nistagram.campaignmicroservice.controller.dto.AdDto;
import com.nistagram.campaignmicroservice.controller.dto.CampaignDto;
import com.nistagram.campaignmicroservice.controller.mapper.AdMapper;
import com.nistagram.campaignmicroservice.controller.mapper.CampaignMapper;
import com.nistagram.campaignmicroservice.domain.Campaign;
import com.nistagram.campaignmicroservice.domain.enums.CampaignType;
import com.nistagram.campaignmicroservice.domain.enums.TermType;
import com.nistagram.campaignmicroservice.exception.InvalidActionException;
import com.nistagram.campaignmicroservice.repository.ICampaignRepository;
import com.nistagram.campaignmicroservice.service.interfaces.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService implements ICampaignService {
    @Autowired
    private ICampaignRepository campaignRepository;
    @Autowired
    private MediaConnection mediaConnection;

    @Override
    public Campaign findById(Long id) {
        return campaignRepository.getOne(id);
    }

    @Override
    public void createCampaign(CampaignDto campaignDto) {
        validateInput(campaignDto);
        for (AdDto ad : campaignDto.getAds()) {
            Long mediaId;
            if(campaignDto.getCampaignType() == CampaignType.POST)
                 mediaId = mediaConnection.saveImageInfo(AdMapper.mapAdDtoToMediaDto(ad, campaignDto.getUsername()));
            else
                mediaId = mediaConnection.saveStoryImageInfo(AdMapper.mapAdDtoToMediaDto(ad, campaignDto.getUsername()));
            ad.setMediaId(mediaId);
            // TODO: Set saved media to campaign.ads.mediaId
        }
        Campaign campaign = CampaignMapper.mapCampaignDtoToCampaign(campaignDto);
        if(campaign.getTermType() == TermType.SHORT_TERM){
            campaign.setPerDay(1);
            campaign.setEndDate(null);
        }
        campaignRepository.save(campaign);
    }

    private void validateInput(CampaignDto campaignDto){
        if(campaignDto.getStartDate().after(campaignDto.getEndDate()))
            throw new InvalidActionException("Invalid date range!");
        if(campaignDto.getTermType() == TermType.LONG_TERM && campaignDto.getPerDay() < 1)
            throw new InvalidActionException("Per day count must be positive number!");
        if(campaignDto.getAds().size() < 1)
            throw new InvalidActionException("At least one ad must be specified!");
        for(AdDto ad : campaignDto.getAds()){
            if(ad.getFileName().trim().equals(""))
                throw new InvalidActionException("Media filename must be specified!");
        }
    }
}
