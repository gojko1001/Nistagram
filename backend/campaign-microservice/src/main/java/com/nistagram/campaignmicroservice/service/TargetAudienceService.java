package com.nistagram.campaignmicroservice.service;

import com.nistagram.campaignmicroservice.connection.MediaConnection;
import com.nistagram.campaignmicroservice.connection.UserConnection;
import com.nistagram.campaignmicroservice.controller.dto.HashtagDto;
import com.nistagram.campaignmicroservice.controller.dto.UserDto;
import com.nistagram.campaignmicroservice.domain.TargetAudience;
import com.nistagram.campaignmicroservice.exception.NotFoundException;
import com.nistagram.campaignmicroservice.repository.ITargetAudienceRepository;
import com.nistagram.campaignmicroservice.service.interfaces.ITargetAudienceService;
import com.nistagram.campaignmicroservice.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class TargetAudienceService implements ITargetAudienceService {
    @Autowired
    private ITargetAudienceRepository targetAudienceRepository;
    @Autowired
    private MediaConnection mediaConnection;
    @Autowired
    private UserConnection userConnection;

    public TargetAudience save(TargetAudience audience){
        return targetAudienceRepository.save(audience);
    }

    @Override
    public boolean isUserTargetedAudience(String username, List<TargetAudience> audiences) {
        UserDto userDto;
        try{
            userDto = userConnection.getUserByUsername(username);
        }catch (Exception e){
            throw new NotFoundException("User not found!");
        }
        for(TargetAudience audience : audiences)
            if(isAudience(userDto, audience))
                return true;
        return false;
    }

    private boolean isAudience(UserDto userDto, TargetAudience audience){
        List<HashtagDto> userHashtags = mediaConnection.getLoggedUsersHashtags(userDto.getUsername());
        if(!audience.getGenders().contains(userDto.getUserGender()))
            return false;
        int userAge = DateTimeUtil.calculateAge(userDto.getBirthDate());
        if(audience.getFromAge() < userAge || audience.getToAge() > userAge)
            return false;
        for(HashtagDto hashtag : userHashtags)
            if(audience.getHashtags().contains(hashtag.getName()))
                return true;
        return false;
    }
}
