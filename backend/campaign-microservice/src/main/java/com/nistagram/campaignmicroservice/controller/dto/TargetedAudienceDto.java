package com.nistagram.campaignmicroservice.controller.dto;

import com.nistagram.campaignmicroservice.domain.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class TargetedAudienceDto {
    private Long campaignId;
    private int fromAge;
    private int toAge;
    private List<String> hashtags;
    private List<Gender> genders;
}
