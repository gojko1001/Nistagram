package com.agent.webshop.controller.dto;

import com.agent.webshop.domain.ItemInCart;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String country;
    private String address;
    private String postalCode;
    private Date timestamp;
    private List<ItemInCart> itemsInCart;
    private float totalPrice;
}
