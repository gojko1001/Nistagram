package com.agent.webshop.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private int quantity = 0;
    @Column
    private boolean onSale = false;
    @Column
    private float price;
    @Column
    private String fileName;
}
