package com.agent.webshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @ManyToMany
    private List<ItemInCart> itemsInCart;
    @Column
    private float totalPrice;
}
