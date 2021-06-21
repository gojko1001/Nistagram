package com.nistagram.authenticationmicroservice.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Data
public class UserCredentials implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
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
        for (Role r : roles) {
            for (Permission p : r.getPermissions()) {
                permissions.add(new SimpleGrantedAuthority(p.getAuthority()));
            }
        }
        return permissions;
    }
}
