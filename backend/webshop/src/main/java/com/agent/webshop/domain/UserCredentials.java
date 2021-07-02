package com.agent.webshop.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class UserCredentials implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column
    private String password;
    @ManyToOne
    private Role role;
    @Column
    private Boolean verified;
    @Column
    private Boolean isDeactivated = false;

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
        return verified;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set permissions = new HashSet();
        for (Permission p : role.getPermissions()) {
            permissions.add(new SimpleGrantedAuthority(p.getAuthority()));
        }
        return permissions;
    }
}
