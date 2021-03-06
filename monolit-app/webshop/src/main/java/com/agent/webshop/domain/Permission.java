package com.agent.webshop.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
@Data
public class Permission implements GrantedAuthority {
    private static final long serialVersionUID = 8610811500072052061L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;

    @Override
    public String getAuthority() {
        return name;
    }
}
