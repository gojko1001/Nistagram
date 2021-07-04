package com.agent.webshop.service;

import com.agent.webshop.domain.ItemInCart;
import com.agent.webshop.repository.IItemInCartRepository;
import com.agent.webshop.service.interfaces.IItemInCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemInCartService implements IItemInCartService {
    @Autowired
    private IItemInCartRepository itemInCartRepository;

    @Override
    public ItemInCart create(ItemInCart itemInCart){
        return itemInCartRepository.save(itemInCart);
    }

}
