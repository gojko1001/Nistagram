package com.xws.nistagrammonolith.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column
    private String fullName;
    @Column(nullable = false, unique = true)
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
    @Column
    private Role userRole;
    @Column
    private Boolean publicProfile;
    @Column
    private Boolean publicDM;
    @Column
    private Boolean taggable;

    public User() {}

    public User(Long id, String username, String fullName, String email, String phone, String webSite,
                String bio, Date birthDate, Gender userGender, Role userRole,
                Boolean publicProfile, Boolean publicDM, Boolean taggable) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.webSite = webSite;
        this.bio = bio;
        this.birthDate = birthDate;
        this.userGender = userGender;
        this.userRole = userRole;
        this.publicProfile = publicProfile;
        this.publicDM = publicDM;
        this.taggable = taggable;
    }
}
