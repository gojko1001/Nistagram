package com.nistagram.usermicroservice.domain;

import com.nistagram.usermicroservice.domain.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "Users")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column
    private String fullName;
    @Column(nullable = false)
    private String email;
    @Column
    private String phone;
    @Column
    private String webSite;
    @Column
    private String bio;
    @Column
    private Date birthDate;
    @Column
    private Gender userGender;
    @OneToMany(mappedBy = "following")
    private List<FollowingList> followingList;
    @OneToOne
    private VerificationRequest verificationRequest;
    @Column
    private boolean publicProfile = false;
    @Column
    private boolean publicDM = true;
    @Column
    private boolean taggable = true;

}
