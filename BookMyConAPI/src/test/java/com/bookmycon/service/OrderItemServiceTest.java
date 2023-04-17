package com.bookmycon.service;

import com.bookmycon.model.Order;
import com.bookmycon.model.OrderItem;
import com.bookmycon.model.Product;
import com.bookmycon.repository.OrderItemRepository;
import com.bookmycon.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    @InjectMocks
    private ProductService productService;
    private Product product;
    private Order order;

    List<OrderItem> orderItems = new ArrayList<>();
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        product = new Product();
        order = new Order();
    }

    @Test
    public void testGetAllProductsForInvalidOrderId() {
        List<OrderItem> orderItems = orderItemService.getAllProducts(0);
        assertNotNull(orderItems);
        assertTrue(orderItems.isEmpty());
    }
    @Test
    public void testSaveProducts() {
        int quantity = 5;

        // Mocking the orderItemRepository.save method
//        doNothing().when(orderItemRepository).save(any(OrderItem.class));

        // Call the method to be tested

        orderItemService.saveProducts(product, order, quantity);

        // Verify that the orderItemRepository.save method was called with the expected parameters
        ArgumentCaptor<OrderItem> orderItemCaptor = ArgumentCaptor.forClass(OrderItem.class);
        verify(orderItemRepository, times(1)).save(orderItemCaptor.capture());
        OrderItem capturedOrderItem = orderItemCaptor.getValue();

        // Assert that the captured orderItem has the expected values
        assertEquals(product, capturedOrderItem.getProduct());
        assertEquals(order, capturedOrderItem.getOrder());
        assertEquals(quantity, capturedOrderItem.getQuantity());
    }


}
