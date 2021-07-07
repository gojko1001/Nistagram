package com.agent.webshop.service;

import com.agent.webshop.controller.dto.OrderDto;
import com.agent.webshop.controller.mapper.OrderMapper;
import com.agent.webshop.domain.ItemInCart;
import com.agent.webshop.domain.Order;
import com.agent.webshop.domain.ShoppingCart;
import com.agent.webshop.repository.IOrderRepository;
import com.agent.webshop.service.interfaces.IOrderService;
import com.agent.webshop.service.interfaces.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IShoppingCartService shoppingCartService;

    @Override
    public Order create(OrderDto orderDto){
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUsername(orderDto.getUsername());
        Order order = OrderMapper.mapOrderDtoToOrder(orderDto);
        order.setTotalPrice(shoppingCart.getTotalPrice());
        List<ItemInCart> itemInCartList = new ArrayList<>();
        for(ItemInCart item : shoppingCart.getItemsInCart()){
            itemInCartList.add(item);
        }
        order.setItemsInCart(itemInCartList);
        orderRepository.save(order);
        shoppingCartService.deleteShoppingCart(shoppingCart);
        return order;
    }

}
