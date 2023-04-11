package com.bookmycon.service;

import com.bookmycon.model.OrderItem;
import com.bookmycon.model.Product;
import com.bookmycon.repository.OrderItemRepository;
import com.bookmycon.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderItemServiceTest {

    @Mock
    OrderItemRepository orderItemRepository;

    @InjectMocks
    OrderItemService orderItemService;

    List<OrderItem> orderItems = new ArrayList<>();

    @Test
    public void testGetAllProductsForInvalidOrderId() {
        List<OrderItem> orderItems = orderItemService.getAllProducts(0);
        assertNotNull(orderItems);
        assertTrue(orderItems.isEmpty());
    }



}
