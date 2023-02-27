package com.bookmycon.service;

import com.bookmycon.model.Booking;
import com.bookmycon.model.Order;
import com.bookmycon.model.User;
import com.bookmycon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /*
     * retrieve Order By Its order ID.
     *
     * @param int orderId
     * @return Order object
     * */
    public Order getOrderById(int id) {
        return orderRepository.findById(id).get();
    }

    /*
     * Save new order of User
     *
     * @param Order object
     * @return String message
     * */
    public Order saveOrder(Order order, double total, Booking booking, User user) {
        order.setTotal(total);
        order.setBooking(booking);
        order.setUser(user);
        return orderRepository.save(order);
    }

    /*
     * Delete order by its orderId
     *
     * @param Integer orderId
     * @return String message
     * */
    public String deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
        return "Order is Canceled";
    }
}
