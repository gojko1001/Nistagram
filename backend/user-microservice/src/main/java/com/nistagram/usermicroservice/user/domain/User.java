package com.nistagram.usermicroservice.user.domain;

import com.nistagram.usermicroservice.user.domain.enums.Gender;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "app_user")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column
    private String profilePicPath;
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRelation> userRelations;
    @OneToMany(mappedBy = "relatedUser", cascade = CascadeType.ALL)
    private List<UserRelation> invertedRelations;
    @OneToOne
    private VerificationRequest verificationRequest;
    @Column
    private boolean publicProfile = false;
    @Column
    private boolean publicDM = true;
    @Column
    private boolean taggable = true;

}
