package com.nistagram.campaignmicroservice.domain;

import com.nistagram.campaignmicroservice.domain.enums.CampaignType;
import com.nistagram.campaignmicroservice.domain.enums.TermType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column
    private CampaignType type;
    @Column
    private TermType termType;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @Column
    private int perDay;
    @Column
    private boolean active = false;
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<Ad> ads;
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<TargetAudience> targetAudience;
}
