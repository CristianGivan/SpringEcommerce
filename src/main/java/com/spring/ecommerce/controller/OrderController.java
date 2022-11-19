package com.spring.ecommerce.controller;

import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/{userId}")
    public Order creteOrderAtUser(@PathVariable Long userId){
    return orderService.placeOrder(userId);
    }
    @GetMapping("/allByUser/{userId}")
    public List<Order> findAllOrdersByUserAndOrderByDate(@PathVariable Long userId){
        List<Order> orders=orderService.findAllOrdersByUserAndOrderByDate(userId);
        return orders;
    }
    //todo nu imi afiseaza in posrtment chair dca eu am acest obiect.
    @GetMapping("/{orderId}")
    public Order findOrderById(@PathVariable Long orderId){
        return orderService.findOrderById(orderId);
    }

}
