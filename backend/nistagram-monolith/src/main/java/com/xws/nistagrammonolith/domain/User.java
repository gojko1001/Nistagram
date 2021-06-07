package com.xws.nistagrammonolith.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "users")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
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
    @Column
    private boolean publicProfile = true;
    @Column
    private boolean publicDM = true;
    @Column
    private boolean taggable = true;

}
