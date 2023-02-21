package com.ioffice.controller;

import com.ioffice.model.Booking;
import com.ioffice.model.Order;
import com.ioffice.model.OrderItem;
import com.ioffice.model.Product;
import com.ioffice.repository.OrderRepository;
import com.ioffice.service.OrderService;
import com.ioffice.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;
    OrderController orderController;
    Order order;
    Map<String, Object> response = new HashMap<>();
    List<OrderItem> orderItemList = new ArrayList<>();
    List<Order> orderList;
    @Before
    public void setUp() {

    }



}