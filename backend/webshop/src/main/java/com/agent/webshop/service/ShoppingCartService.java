package com.agent.webshop.service;

import com.agent.webshop.domain.ItemInCart;
import com.agent.webshop.domain.ShoppingCart;
import com.agent.webshop.repository.IShoppingCartRepository;
import com.agent.webshop.service.interfaces.IItemInCartService;
import com.agent.webshop.service.interfaces.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService implements IShoppingCartService {
    @Autowired
    private IShoppingCartRepository shoppingCartRepository;
    @Autowired
    private IItemInCartService itemInCartService;

    @Override
    public ShoppingCart getShoppingCartByUsername(String username){
        return shoppingCartRepository.findShoppingCartByUsername(username);
    }

    @Override
    public ShoppingCart addItemInCart(String username, ItemInCart itemInCart){
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUsername(username);
        boolean alreadyExist = false;
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
            shoppingCart.setUsername(username);
            shoppingCartRepository.save(shoppingCart);
        }
        List<ItemInCart> itemInCartList = shoppingCart.getItemsInCart();
        // if already exist in cart
        if(shoppingCart.getItemsInCart() != null){
            for(ItemInCart item : itemInCartList){
                if(item.getItem().getId().equals(itemInCart.getItem().getId())){
                    item.setQuantity(item.getQuantity() + 1);
                    alreadyExist = true;
                }
            }
        }else{
            itemInCartList = new ArrayList<>();
        }
        if(alreadyExist == false){
            ItemInCart itemInCartDb = new ItemInCart();
            itemInCartDb.setItem(itemInCart.getItem());
            itemInCartService.create(itemInCartDb);
            itemInCartList.add(itemInCartDb);
            shoppingCart.setItemsInCart(itemInCartList);
        }
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + itemInCart.getItem().getPrice());
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart deleteItemFromShoppingCart(String username, Long itemInCartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUsername(username);
        List<ItemInCart> itemInCartList = shoppingCart.getItemsInCart();
        for(ItemInCart item : itemInCartList){
            if(item.getId().equals(itemInCartId)){
                itemInCartList.remove(item);
                break;
            }
        }
        ItemInCart itemInCart = itemInCartService.findById(itemInCartId);
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - (itemInCart.getQuantity() * itemInCart.getItem().getPrice()));
        itemInCartService.delete(itemInCart);
        shoppingCart.setItemsInCart(itemInCartList);
        return shoppingCartRepository.save(shoppingCart);
    }

}
