package com.agent.webshop.service.interfaces;

import com.agent.webshop.controller.dto.OrderDto;
import com.agent.webshop.domain.Order;

public interface IOrderService {
    Order create(OrderDto orderDto);
}
