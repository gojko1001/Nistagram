package com.nistagram.campaignmicroservice.controller.dto;

import com.nistagram.campaignmicroservice.domain.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class TargetAudienceDto {
    private int fromAge;
    private int toAge;
    private List<String> hashtags;
    private List<Gender> genders;
}
