package com.bookmycon.controller;


import com.bookmycon.dto.OrderContentDto;
import com.bookmycon.model.*;
import com.bookmycon.repository.BookingRepository;
import com.bookmycon.service.BookingService;
import com.bookmycon.service.OrderItemService;
import com.bookmycon.service.OrderService;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;

    @Mock
    private BookingService bookingService;
    @Mock
    BookingRepository bookingRepo;

    @Mock
    private OrderItemService orderItemService;
    int orderId;

    OrderContentDto orderContentDto;
    Order order;
    Booking booking;

    Product product;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        orderContentDto = new OrderContentDto();
        bookingService = new BookingService();
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







