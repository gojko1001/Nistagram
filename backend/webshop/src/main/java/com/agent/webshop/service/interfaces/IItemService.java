package com.agent.webshop.service.interfaces;

import com.agent.webshop.domain.Item;

import java.util.List;

public interface IItemService {
    Item create(Item item);
    List<Item> getItemsByUsername(String username);
    List<Item> getItems();
    Item updateItem(Item newItem);
}
