package com.nistagram.campaignmicroservice.controller.dto;

import com.nistagram.campaignmicroservice.domain.enums.CampaignType;
import com.nistagram.campaignmicroservice.domain.enums.TermType;
import lombok.Data;

import java.util.Date;

@Data
public class CampaignDto {
    private String username;
    private CampaignType campaignType;
    private TermType termType;
    private Date startDate;
    private Date endDate;
    private int perDay;
}
