package com.xws.nistagrammonolith.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Table
@Entity
@Getter
@Setter
public class UserCredentials implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String salt;
    @Enumerated(EnumType.ORDINAL)
    private Role userRole;

    public UserCredentials(){}

    public UserCredentials(Long id, String username, String password, String salt, Role userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.userRole = userRole;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Authority> authorities = new ArrayList<>();
        Authority authority = new Authority();
        authority.setName(userRole.name());
        authorities.add(authority);
        return authorities;
    }
}
