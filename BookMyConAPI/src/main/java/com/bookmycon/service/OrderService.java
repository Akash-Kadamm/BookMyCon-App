package com.bookmycon.service;

import com.bookmycon.model.Booking;
import com.bookmycon.model.Order;
import com.bookmycon.model.User;
import com.bookmycon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    Logger logger=Logger.getLogger(OrderService.class);

    /*
     * retrieve Order By Its order ID.
     *
     * @param int orderId
     * @return Order object
     * */
    public Order getOrderById(int id) {
        logger.info("Getting order with id: {}" + id);
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
        logger.info("Saving order with details: total={}" + total + "booking_id={} " + booking.getBookingId() + " user_id={} " + user.getUserId());
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
        logger.info("Deleting order with id: {}" + orderId);
        return "Order is Canceled";
    }
}
