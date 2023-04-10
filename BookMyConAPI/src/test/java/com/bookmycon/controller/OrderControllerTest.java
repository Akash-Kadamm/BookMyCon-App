package com.bookmycon.controller;


import com.bookmycon.model.*;
import com.bookmycon.service.BookingService;
import com.bookmycon.service.OrderItemService;
import com.bookmycon.service.OrderService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;
    int orderId;
    Order order;
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }
    @Test
    public void testFindOrder() {
        int orderId = 1;
        Order expectedOrder = new Order();
        when(orderService.getOrderById(orderId)).thenReturn(expectedOrder);
        ResponseEntity<Order> responseEntity = orderController.findOrder(orderId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedOrder, responseEntity.getBody());
        verify(orderService, times(1)).getOrderById(orderId);
    }

     @Test
    public void testCancelOrder_Success() {
        int orderId = 1;
        String expectedResponse = "Order with id " + orderId + " cancelled successfully";
        when(orderService.deleteOrder(orderId)).thenReturn(expectedResponse);
        ResponseEntity<String> responseEntity = orderController.cancelOrder(orderId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

}