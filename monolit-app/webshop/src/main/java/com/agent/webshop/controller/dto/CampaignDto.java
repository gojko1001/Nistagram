package com.agent.webshop.controller.dto;

import com.agent.webshop.controller.dto.enums.CampaignType;
import com.agent.webshop.controller.dto.enums.TermType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class CampaignDto {
    private String username;
    private CampaignType campaignType;
    private TermType termType;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDate;
    private int perDay;
    private List<TargetAudienceDto> audiences;
    private List<AdDto> ads;
}
