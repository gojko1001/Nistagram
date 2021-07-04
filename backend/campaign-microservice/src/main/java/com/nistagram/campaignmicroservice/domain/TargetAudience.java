package com.nistagram.campaignmicroservice.domain;

import com.nistagram.campaignmicroservice.domain.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class TargetAudience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Campaign campaign;
//    @ElementCollection
//    private List<Integer> ageRange;
    @Column
    private int fromAge;
    @Column
    private int toAge;
    @ElementCollection
    private List<String> hashtags;
    @ElementCollection
    private List<Gender> genders;
}
