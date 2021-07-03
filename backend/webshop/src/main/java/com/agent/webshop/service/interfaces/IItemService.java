package com.agent.webshop.service.interfaces;

import com.agent.webshop.domain.Item;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IItemService {
    Item create(Item item);
    List<Item> getItemsByUsername(String username);
    List<Item> getItems();
    ResponseEntity updateItem(String username, Item newItem);
    ResponseEntity delete(String username, Long itemId);
}
