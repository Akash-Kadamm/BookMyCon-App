package com.bookmycon.controller;

import com.bookmycon.model.OrderItem;
import com.bookmycon.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderItems")
public class  OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Operation(summary = "Fetch order items by id ", description = "Fetch order items by ID")
    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderItem>> getAllOrderItems(@PathVariable int id){
        return new ResponseEntity<>(
                orderItemService.getAllProducts(id),
                HttpStatus.OK
        );
    }

}
