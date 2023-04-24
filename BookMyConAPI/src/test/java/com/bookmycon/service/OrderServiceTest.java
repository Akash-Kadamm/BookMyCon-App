package com.bookmycon.service;

import com.bookmycon.model.Booking;
import com.bookmycon.model.Order;
import com.bookmycon.model.Product;
import com.bookmycon.model.User;
import com.bookmycon.repository.OrderRepository;
import com.bookmycon.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.bookmycon.service.GuestService.logger;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    List<Order> orderList = new ArrayList<>();
    Order order;


    @Test
    public void testGetOrderByIdInvalidId() {
        // Arrange
        int id = 1;

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> orderService.getOrderById(id));
    }

    @Test
    public void testGetOrderByIdNullId() {
        // Arrange
        Integer orderId = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> orderService.getOrderById(orderId));
    }

    @Test
    public void testDeleteOrder() {
        int orderId = 1;
        String expectedResult = "Order is Canceled";

        doNothing().when(orderRepository).deleteById(orderId);

        String result = orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);

        assertEquals(expectedResult, result);
    }



}
