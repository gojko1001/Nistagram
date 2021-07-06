package com.nistagram.campaignmicroservice.controller.mapper;

import com.nistagram.campaignmicroservice.controller.dto.TargetAudienceDto;
import com.nistagram.campaignmicroservice.domain.TargetAudience;

import java.util.ArrayList;
import java.util.List;

public class TargetAudienceMapper {

    public static TargetAudience mapAudienceDtoToAudience(TargetAudienceDto audienceDto){
        TargetAudience audience = new TargetAudience();
        audience.setFromAge(audienceDto.getFromAge());
        audience.setToAge(audienceDto.getToAge());
        audience.setGenders(audienceDto.getGenders());
        audience.setHashtags(audienceDto.getHashtags());
        return audience;
    }

    public static List<TargetAudience> mapAudiencesDtoToAudiences(List<TargetAudienceDto> audienceDtos){
        List<TargetAudience> audiences = new ArrayList<>();
        for (TargetAudienceDto a : audienceDtos)
            audiences.add(mapAudienceDtoToAudience(a));
        return audiences;
    }
}
