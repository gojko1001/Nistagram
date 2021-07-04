package com.agent.webshop.controller;

import com.agent.webshop.domain.ItemInCart;
import com.agent.webshop.domain.ShoppingCart;
import com.agent.webshop.service.interfaces.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shopping-cart")
@CrossOrigin(origins = "http://localhost:4200")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService shoppingCartService;

    @GetMapping("/{username}")
    public ShoppingCart getShoppingCartByUsername(@PathVariable("username") String username) {
        return shoppingCartService.getShoppingCartByUsername(username);
    }

    @PostMapping("/add/{username}")
    public ShoppingCart addItemInCart(@PathVariable("username") String username, @RequestBody ItemInCart itemInCart){
        return shoppingCartService.addItemInCart(username, itemInCart);
    }

    @DeleteMapping("/delete/{username}/{itemInCartId}")
    public ShoppingCart deleteItemFromCart(@PathVariable("username") String username, @PathVariable("itemInCartId") Long itemInCartId){
        return shoppingCartService.deleteItemFromShoppingCart(username, itemInCartId);
    }

}
