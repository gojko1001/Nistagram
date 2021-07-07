package com.agent.webshop.service.interfaces;

import com.agent.webshop.controller.dto.ImageBytesDto;
import com.agent.webshop.domain.Item;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IItemService {
    Item create(Item item);
    List<ImageBytesDto> getItemsByUsername(String username);
    ResponseEntity getItems();
    Item getItemById(Long id);
    ResponseEntity updateItem(String username, Item newItem);
    ResponseEntity delete(String username, Long itemId);
    List<ImageBytesDto> getImagesFiles(List<Item> items);
    ImageBytesDto imageFile(Item item, String filePath);
}
