package com.bookmycon.service;

import com.bookmycon.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @Test
    public void testGetOrderByIdInvalidId() {
        int id = 1;
        assertThrows(NoSuchElementException.class, () -> orderService.getOrderById(id));
    }

    @Test
    public void testGetOrderByIdNullId() {
        Integer orderId = null;
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
