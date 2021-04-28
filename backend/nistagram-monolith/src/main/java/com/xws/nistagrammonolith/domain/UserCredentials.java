package com.xws.nistagrammonolith.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

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
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> userRoles;
    @Column
    private Boolean verified;

    public UserCredentials(){}

    public UserCredentials(Long id, String username, String password, String salt, List<Authority> userRole, Boolean verified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.userRoles = userRole;
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
//        Collection<Authority> authorities = new ArrayList<>();
//        Authority authority = new Authority();
//        authority.setName(userRole.getName());
//        authorities.add(authority);
        return userRoles;
    }
}
