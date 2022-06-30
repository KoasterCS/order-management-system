package com.order.management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.management.dto.OrdersDto;
import com.order.management.entity.OrdersEntity;
import com.order.management.service.OrderService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/api/1.0/orders")
    @ApiOperation(value = "Get All Orders", response = OrdersEntity.class)
    ResponseEntity<List<OrdersEntity>> getOrders(){
        return new ResponseEntity<>(orderService.findAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/api/1.0/orders/save")
    @ApiOperation(value = "Save All Orders", response = OrdersEntity.class)
    ResponseEntity<OrdersEntity> saveOrder(@RequestBody OrdersDto orders){
        return new ResponseEntity<>(orderService.saveNewOrders(orders), HttpStatus.OK);
    }
}
