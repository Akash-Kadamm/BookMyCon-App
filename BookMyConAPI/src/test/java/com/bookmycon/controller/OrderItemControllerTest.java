package com.bookmycon.controller;

import com.bookmycon.model.OrderItem;
import com.bookmycon.model.Product;
import com.bookmycon.service.OrderItemService;
import com.bookmycon.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OrderItemControllerTest {

    @Mock
    OrderItemService orderItemService;

    @InjectMocks
    OrderItemController orderItemController;

    @Test
    public void testGetAllOrderItems() {
        int orderId = 1;
        List<OrderItem> orderItems = new ArrayList<>();

        when(orderItemService.getAllProducts(orderId)).thenReturn(orderItems);

        ResponseEntity<List<OrderItem>> response = orderItemController.getAllOrderItems(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderItems, response.getBody());
    }
}
