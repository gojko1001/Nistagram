package com.agent.webshop.controller;

import com.agent.webshop.controller.dto.OrderDto;
import com.agent.webshop.domain.Order;
import com.agent.webshop.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping
    public Order create(@RequestBody OrderDto orderDto){
        return orderService.create(orderDto);
    }

}
