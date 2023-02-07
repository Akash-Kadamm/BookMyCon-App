package com.ioffice.controller;

import com.ioffice.model.Order;

import com.ioffice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*
    * Fetch Order by Order Id.
    *
    * @param int Order id
    * @return Response Entity object
    * */
    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Order> findOrder(@PathVariable  int id){
        return new ResponseEntity<>(
                orderService.getOrderById(id),
                HttpStatus.OK
        );
    }


    /*
    *Making order
    *
    * @param Order object
    * @return ResponseEntity object
    * */
    @PostMapping("/makeOrder")
    public ResponseEntity<String> createOrder(@RequestBody  Order order){
        return new ResponseEntity<>(
          orderService.saveOrder(order),
          HttpStatus.CREATED
        );
    }

    /*
    * Cancel Order
    *
    * @param integer id
    * @return ResponseEntity Object
    * */
    @DeleteMapping("/cancelOrder/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable int orderId){
        return  new ResponseEntity<>(
                orderService.deleteOrder(orderId),
                HttpStatus.OK
        );
    }


}
