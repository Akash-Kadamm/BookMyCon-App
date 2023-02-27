package com.bookmycon.controller;

import com.bookmycon.model.Order;
import com.bookmycon.model.OrderItem;
import com.bookmycon.repository.OrderRepository;
import com.bookmycon.service.OrderService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

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