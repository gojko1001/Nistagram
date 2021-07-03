package com.agent.webshop.controller;

import com.agent.webshop.domain.Item;
import com.agent.webshop.service.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
    @Autowired
    private IItemService itemService;

    @PostMapping
    public Item create(@RequestBody Item item) {
        return itemService.create(item);
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/{username}")
    public List<Item> getItemsByUsername(@PathVariable("username") String username) {
        return itemService.getItemsByUsername(username);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity updateItem(@PathVariable("username") String username, @RequestBody Item newItem) {
        return itemService.updateItem(username, newItem);
    }

    @DeleteMapping("/{username}/{itemId}")
    public ResponseEntity delete(@PathVariable("username") String username,@PathVariable("itemId") Long itemId){
        return itemService.delete(username, itemId);
    }

}
