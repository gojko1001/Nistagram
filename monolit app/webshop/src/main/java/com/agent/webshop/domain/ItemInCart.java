package com.agent.webshop.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ItemInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Item item;
    @Column
    private int quantity = 1;
}
