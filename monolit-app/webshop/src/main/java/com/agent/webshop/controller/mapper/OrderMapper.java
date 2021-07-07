package com.agent.webshop.controller.mapper;

import com.agent.webshop.controller.dto.OrderDto;
import com.agent.webshop.domain.Order;

import java.util.Date;

public class OrderMapper {
    public static Order mapOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        Date currentDate = new Date();
        order.setUsername(orderDto.getUsername());
        order.setFullName(orderDto.getFullName());
        order.setEmail(orderDto.getEmail());
        order.setPhone(orderDto.getPhone());
        order.setCountry(orderDto.getCountry());
        order.setAddress(orderDto.getAddress());
        order.setPostalCode(orderDto.getPostalCode());
        order.setTimestamp(currentDate);
        return order;
    }
}
