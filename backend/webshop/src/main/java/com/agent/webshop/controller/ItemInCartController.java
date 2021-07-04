package com.agent.webshop.controller;

import com.agent.webshop.service.interfaces.IItemInCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item-in-cart")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemInCartController {

    @Autowired
    private IItemInCartService itemInCartService;


}
