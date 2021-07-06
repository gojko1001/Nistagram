package com.agent.webshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "orders")
@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String fullName;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String country;
    @Column
    private String address;
    @Column
    private String postalCode;
    @Column
    private Date timestamp;
    @ManyToMany
    private List<ItemInCart> itemsInCart;
    @Column
    private float totalPrice;
}
