package com.bookmycon.controller;

import com.bookmycon.dto.OrderContentDto;
import com.bookmycon.model.Order;
import com.bookmycon.model.Product;
import com.bookmycon.service.BookingService;
import com.bookmycon.service.OrderItemService;
import com.bookmycon.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {
    Logger logger = Logger.getLogger(OrderController.class);
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    BookingService bookingService;
    @Autowired
    private OrderService orderService;

    @Operation(summary = "To Fetch Order By ID", description = "To Fetch Order By ID ")
    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Order> findOrder(@PathVariable int id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }
    @Operation(summary = "To Cancel Order By ID", description = "To Cancel Order By ID ")
    @DeleteMapping("/cancelOrder/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable int orderId) {
        return new ResponseEntity<>(orderService.deleteOrder(orderId), HttpStatus.OK);
    }

    @Operation(summary = "To Place Order By booking ID", description = "To Place Order By booking ID ")
    @PostMapping("/place-order/{bookingId}")
    public ResponseEntity<?> placeOrder(@RequestBody OrderContentDto orderContent, @PathVariable int bookingId) {
        Order userOrder = new Order();
        Order order = orderService.saveOrder(userOrder, orderContent.getTotal(), bookingService.getBookingById(bookingId).get(), orderContent.getUser());

        for (Product product : orderContent.getProductList()) {
            orderItemService.saveProducts(product, order, product.getQuantity());
        }
        logger.info("Customer " + order.getUser().getUserName() + " Placed Order");
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
