package com.nistagram.campaignmicroservice.controller.mapper;

import com.nistagram.campaignmicroservice.controller.dto.CampaignDto;
import com.nistagram.campaignmicroservice.domain.Campaign;

import java.util.ArrayList;
import java.util.List;

public class CampaignMapper {

    public static Campaign mapCampaignDtoToCampaign(CampaignDto campaignDto){
        Campaign campaign = new Campaign();
        campaign.setUsername(campaignDto.getUsername());
        campaign.setType(campaignDto.getCampaignType());
        campaign.setTermType(campaignDto.getTermType());
        campaign.setStartDate(campaignDto.getStartDate());
        campaign.setEndDate(campaignDto.getEndDate());
        campaign.setPerDay(campaignDto.getPerDay());
        campaign.setTargetAudience(TargetAudienceMapper.mapAudiencesDtoToAudiences(campaignDto.getAudiences()));
        campaign.setAds(AdMapper.mapAdsDtoToAds(campaignDto.getAds()));
        return campaign;
    }

    public static CampaignDto mapCampaignToCampaignDto(Campaign campaign){
        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setUsername(campaign.getUsername());
        campaignDto.setCampaignType(campaign.getType());
        campaignDto.setTermType(campaign.getTermType());
        campaignDto.setStartDate(campaign.getStartDate());
        campaignDto.setEndDate(campaign.getEndDate());
        campaignDto.setPerDay(campaign.getPerDay());
        return campaignDto;
    }

    public static List<CampaignDto> mapCampaignsToCampaignsDto(List<Campaign> campaigns){
        List<CampaignDto> campaignsDto = new ArrayList<>();
        for(Campaign c : campaigns)
            campaignsDto.add(mapCampaignToCampaignDto(c));
        return campaignsDto;
    }
}
