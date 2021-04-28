package com.xws.nistagrammonolith.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    @Column
    private Boolean verified;

    public UserCredentials(){}

    public UserCredentials(Long id, String username, String password, String salt, Collection<Role> roles, Boolean verified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.roles = roles;
        this.verified = verified;
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
        return verified;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set permissions = new HashSet();
        for(Role r : roles){
            for(Permission p : r.getPermissions()){
                permissions.add(new SimpleGrantedAuthority(p.getAuthority()));
            }
        }
        return permissions;
    }
}
