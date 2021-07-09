package com.agent.webshop.service;

import com.agent.webshop.controller.dto.OrderDto;
import com.agent.webshop.controller.mapper.OrderMapper;
import com.agent.webshop.domain.Item;
import com.agent.webshop.domain.ItemInCart;
import com.agent.webshop.domain.Order;
import com.agent.webshop.domain.ShoppingCart;
import com.agent.webshop.repository.IOrderRepository;
import com.agent.webshop.service.interfaces.IItemService;
import com.agent.webshop.service.interfaces.IOrderService;
import com.agent.webshop.service.interfaces.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    private IItemService itemService;

    @Override
    public ResponseEntity create(OrderDto orderDto){
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUsername(orderDto.getUsername());
        Order order = OrderMapper.mapOrderDtoToOrder(orderDto);
        order.setTotalPrice(shoppingCart.getTotalPrice());
        List<ItemInCart> itemInCartList = new ArrayList<>();
        for(ItemInCart item : shoppingCart.getItemsInCart()){
            Item curr = itemService.getItemById(item.getItem().getId());
            if(curr.getQuantity() - item.getQuantity() < 0){
                return new ResponseEntity("Error occurred. Problem with " + curr.getName() + " item.", HttpStatus.BAD_REQUEST);
            }
        }
        for(ItemInCart item : shoppingCart.getItemsInCart()){
            itemInCartList.add(item);
            Item curr = itemService.getItemById(item.getItem().getId());
            if(curr.getQuantity() - item.getQuantity() >= 0){
                curr.setQuantity(curr.getQuantity() - item.getQuantity());
                itemService.create(curr);
            }else{
                return new ResponseEntity("Error occurred. Problem with " + curr.getName() + " item.", HttpStatus.BAD_REQUEST);
            }
        }
        order.setItemsInCart(itemInCartList);
        orderRepository.save(order);
        shoppingCartService.deleteShoppingCart(shoppingCart);
        return new ResponseEntity(HttpStatus.OK);
    }

}
