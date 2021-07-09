package com.agent.webshop.service;

import com.agent.webshop.domain.ItemInCart;
import com.agent.webshop.repository.IItemInCartRepository;
import com.agent.webshop.service.interfaces.IItemInCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInCartService implements IItemInCartService {
    @Autowired
    private IItemInCartRepository itemInCartRepository;

    @Override
    public ItemInCart create(ItemInCart itemInCart){
        return itemInCartRepository.save(itemInCart);
    }

    @Override
    public ItemInCart findById(Long id) { return itemInCartRepository.findItemInCartById(id); }

    @Override
    public void delete(ItemInCart itemInCart){ itemInCartRepository.delete(itemInCart);}

    @Override
    public List<ItemInCart> findAll(){
        return itemInCartRepository.findAll();
    }

}
