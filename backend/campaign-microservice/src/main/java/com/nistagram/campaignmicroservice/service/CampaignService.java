package com.nistagram.campaignmicroservice.service;

import com.nistagram.campaignmicroservice.connection.MediaConnection;
import com.nistagram.campaignmicroservice.controller.dto.AdDto;
import com.nistagram.campaignmicroservice.controller.dto.CampaignDto;
import com.nistagram.campaignmicroservice.controller.mapper.AdMapper;
import com.nistagram.campaignmicroservice.controller.mapper.CampaignMapper;
import com.nistagram.campaignmicroservice.domain.Ad;
import com.nistagram.campaignmicroservice.domain.Campaign;
import com.nistagram.campaignmicroservice.domain.TargetAudience;
import com.nistagram.campaignmicroservice.domain.enums.CampaignType;
import com.nistagram.campaignmicroservice.domain.enums.TermType;
import com.nistagram.campaignmicroservice.exception.InvalidActionException;
import com.nistagram.campaignmicroservice.repository.ICampaignRepository;
import com.nistagram.campaignmicroservice.service.interfaces.IAdService;
import com.nistagram.campaignmicroservice.service.interfaces.ICampaignService;
import com.nistagram.campaignmicroservice.service.interfaces.ITargetAudienceService;
import com.nistagram.campaignmicroservice.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CampaignService implements ICampaignService {
    @Autowired
    private ICampaignRepository campaignRepository;
    @Autowired
    private IAdService adService;
    @Autowired
    private ITargetAudienceService targetAudienceService;
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
        }
        Campaign campaign = CampaignMapper.mapCampaignDtoToCampaign(campaignDto);
        if(campaign.getTermType() == TermType.SHORT_TERM){
            campaign.setPerDay(1);
            campaign.setEndDate(null);
        }
        Campaign campaignDb = campaignRepository.save(campaign);
        for(Ad ad : campaign.getAds()){
            ad.setCampaign(campaignDb);
            adService.save(ad);
        }
        for(TargetAudience audience : campaign.getTargetAudience()){
            audience.setCampaign(campaignDb);
            targetAudienceService.save(audience);
        }
    }

    @Override
    public List<Long> getAdvertisements(String username, CampaignType type) {
        Set<Long> mediaToDisplayId = new HashSet<>();
        for(Campaign campaign : campaignRepository.findCampaignsByType(type)){
            if(campaign.getType() != type)
                continue;
            if(!campaign.getTargetAudience().isEmpty() &&
               !targetAudienceService.isUserTargetedAudience(username, campaign.getTargetAudience()))
                continue;

            if(campaign.getTermType() == TermType.SHORT_TERM && campaign.getStartDate().after(new Date())
                && campaign.getStartDate().before(DateTimeUtil.addMinutes(new Date(), 30))){
                mediaToDisplayId.addAll(getMediaIds(campaign.getAds()));
            }
            if(campaign.getTermType() == TermType.LONG_TERM && campaign.getStartDate().before(new Date()) && campaign.getEndDate().after(new Date())){
                for(String time : DateTimeUtil.getDayParts(campaign.getPerDay())){
                    Date d = DateTimeUtil.mergeDateAndTime(new Date(), DateTimeUtil.getDateFromString(time));
                    if(d.after(new Date()) && d.before(DateTimeUtil.addMinutes(new Date(),10))){
                        mediaToDisplayId.addAll(getMediaIds(campaign.getAds()));
                    }
                }
            }
        }
        return (List<Long>) mediaToDisplayId;
    }

    @Override
    public List<String> uploadImages(List<MultipartFile> multipartFiles) {
        return mediaConnection.saveImage(multipartFiles);
    }

    private List<Long> getMediaIds(List<Ad> ads){
        Set<Long> ids = new HashSet<>();
        for(Ad ad : ads)
            ids.add(ad.getMediaId());
        return (List<Long>) ids;
    }

    private void validateInput(CampaignDto campaignDto){
        if(campaignDto.getTermType() == TermType.LONG_TERM && campaignDto.getStartDate().after(campaignDto.getEndDate()))
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
