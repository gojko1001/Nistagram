package com.xws.nistagrammonolith.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "blacklist")
@Entity
@Getter
@Setter
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String password;

    public BlackList() {
    }

    public BlackList(Long id, String password) {
        this.id = id;
        this.password = password;
    }
}
