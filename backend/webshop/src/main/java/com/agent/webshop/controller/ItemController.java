package com.agent.webshop.controller;

import com.agent.webshop.domain.Item;
import com.agent.webshop.service.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
