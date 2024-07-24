package com.progarammingtechie.order_service.controller;

import com.progarammingtechie.order_service.dto.OrderRequese;
import com.progarammingtechie.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oder")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequese orderRequese) {
        orderService.placeOrder(orderRequese);
        return "Order Placed Successfully";
    }
}
