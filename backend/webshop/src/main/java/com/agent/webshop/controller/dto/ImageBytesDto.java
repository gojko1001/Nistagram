package com.agent.webshop.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageBytesDto {
    private Long id;
    private String username;
    private String description;
    private String name;
    private int quantity;
    private boolean onSale;
    private float price;
    private List<ImageByte> imageBytes;
}
