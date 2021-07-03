package com.agent.webshop.service;

import com.agent.webshop.domain.Item;
import com.agent.webshop.repository.IItemRepository;
import com.agent.webshop.service.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity updateItem(String username, Item newItem){
        Item item = itemRepository.findItemById(newItem.getId());
        if(!item.getUsername().equals(username)){
            return new ResponseEntity("You don't have permission to modify item.", HttpStatus.BAD_REQUEST);
        }
        if(item == null){
            return  new ResponseEntity("Item doesn't exist.", HttpStatus.BAD_REQUEST);
        }
        item.setName(newItem.getName());
        item.setDescription(newItem.getDescription());
        item.setQuantity(newItem.getQuantity());
        //item.setImage(newItem.getImage());
        item.setPrice(newItem.getPrice());
        item.setOnSale(newItem.isOnSale());
        itemRepository.save(item);
        return new ResponseEntity("Item has been updated.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(String username, Long itemId){
        Item item = itemRepository.findItemById(itemId);
        if(item == null){
            return  new ResponseEntity("Item doesn't exist.", HttpStatus.BAD_REQUEST);
        }
        if(!item.getUsername().equals(username)){
            return new ResponseEntity("You don't have permission to delete item.", HttpStatus.BAD_REQUEST);
        }
        itemRepository.delete(item);
        // TODO: obrisi taj item u svim korpama
        return new ResponseEntity("Item has been deleted.", HttpStatus.OK);
    }


}
