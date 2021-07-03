package com.agent.webshop.service;

import com.agent.webshop.domain.Item;
import com.agent.webshop.repository.IItemRepository;
import com.agent.webshop.service.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService {
    @Autowired
    private IItemRepository itemRepository;

    @Override
    public Item create(Item item){
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getItemsByUsername(String username){
        return itemRepository.findItemsByUsername(username);
    }

    @Override
    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    @Override
    public Item updateItem(Item newItem){
        Item item = itemRepository.findItemById(newItem.getId());
        item.setName(newItem.getName());
        item.setDescription(newItem.getDescription());
        item.setQuantity(newItem.getQuantity());
        //item.setImage(newItem.getImage());
        item.setPrice(newItem.getPrice());
        item.setOnSale(newItem.isOnSale());
        return itemRepository.save(item);
    }


}
