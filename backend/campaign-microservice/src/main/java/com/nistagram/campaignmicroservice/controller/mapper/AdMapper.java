package com.nistagram.campaignmicroservice.controller.mapper;

import com.nistagram.campaignmicroservice.controller.dto.AdDto;
import com.nistagram.campaignmicroservice.controller.dto.MediaDto;
import com.nistagram.campaignmicroservice.controller.dto.MediaName;
import com.nistagram.campaignmicroservice.domain.Ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdMapper {

    public static Ad mapAdDtoToAd(AdDto adDto){
        Ad ad = new Ad();
        ad.setUrl(adDto.getUrl());
        ad.setMediaId(adDto.getMediaId());
        return ad;
    }

    public static List<Ad> mapAdsDtoToAds(List<AdDto> adDtos){
        List<Ad> ads = new ArrayList<>();
        for(AdDto a : adDtos)
            ads.add(mapAdDtoToAd(a));
        return ads;
    }

    public static MediaDto mapAdDtoToMediaDto(AdDto adDto, String username){
        MediaDto mediaDto = new MediaDto();
        mediaDto.setUsername(username);
        mediaDto.setDescription(adDto.getDescription());
        mediaDto.setFileNames(Collections.singletonList(adDto.getFileName()));
        mediaDto.setMediaName(mapAdDtoToMediaName(adDto));
        return mediaDto;
    }

    public static MediaName mapAdDtoToMediaName(AdDto adDto){
        MediaName mediaName = new MediaName();
        mediaName.setFileName(adDto.getFileName());
        mediaName.setImage(!adDto.getFileName().endsWith(".mp4"));
        return mediaName;
    }
}
