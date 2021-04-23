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
    private Role userRole; // TODO: (nullable = false)
    @Column
    private boolean publicProfile = true;
    @Column
    private boolean publicDM = true;
    @Column
    private boolean taggable = true;

    public User() {}

    public User(Long id, String username, String fullName, String email, String phone, String webSite, String bio, Date birthDate, Gender userGender, Role userRole, boolean publicProfile, boolean publicDM, boolean taggable) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getUserGender() {
        return userGender;
    }

    public void setUserGender(Gender userGender) {
        this.userGender = userGender;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public boolean isPublicProfile() {
        return publicProfile;
    }

    public void setPublicProfile(boolean publicProfile) {
        this.publicProfile = publicProfile;
    }

    public boolean isPublicDM() {
        return publicDM;
    }

    public void setPublicDM(boolean publicDM) {
        this.publicDM = publicDM;
    }

    public boolean isTaggable() {
        return taggable;
    }

    public void setTaggable(boolean taggable) {
        this.taggable = taggable;
    }
}
