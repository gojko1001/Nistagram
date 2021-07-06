package com.agent.webshop.service.interfaces;

import com.agent.webshop.domain.ItemInCart;

public interface IItemInCartService {
    ItemInCart create(ItemInCart itemInCart);
    void delete(ItemInCart itemInCart);
    ItemInCart findById(Long id);

}
