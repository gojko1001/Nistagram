package com.agent.webshop.service.interfaces;

import com.agent.webshop.controller.dto.OrderDto;
import com.agent.webshop.domain.Order;
import org.springframework.http.ResponseEntity;

public interface IOrderService {
    ResponseEntity create(OrderDto orderDto);
}
