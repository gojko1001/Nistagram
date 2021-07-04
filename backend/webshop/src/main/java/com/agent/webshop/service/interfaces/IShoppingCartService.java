package com.agent.webshop.service.interfaces;

import com.agent.webshop.domain.ItemInCart;
import com.agent.webshop.domain.ShoppingCart;

public interface IShoppingCartService {
    ShoppingCart getShoppingCartByUsername(String username);
    ShoppingCart addItemInCart(String username, ItemInCart itemInCart);
    ShoppingCart deleteItemFromShoppingCart(String username, Long itemInCartId);
}
