package com.agent.webshop.controller.mapper;

import com.agent.webshop.controller.dto.ImageBytesDto;
import com.agent.webshop.domain.Item;

import java.util.ArrayList;

public class ItemMapper {
        public static ImageBytesDto mapItemToImageBytesDto(Item item) {
            ImageBytesDto imageBytesDto = new ImageBytesDto();
            imageBytesDto.setId(item.getId());
            imageBytesDto.setUsername(item.getUsername());
            imageBytesDto.setImageBytes(new ArrayList<>());
            imageBytesDto.setDescription(item.getDescription());
            imageBytesDto.setName(item.getName());
            imageBytesDto.setOnSale(item.isOnSale());
            imageBytesDto.setPrice(item.getPrice());
            imageBytesDto.setQuantity(item.getQuantity());
            return imageBytesDto;
        }
}
