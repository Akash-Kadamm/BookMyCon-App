package com.bookmycon.service;

import com.bookmycon.model.Order;
import com.bookmycon.model.OrderItem;
import com.bookmycon.model.Product;
import com.bookmycon.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    /*
     * Fetch OrderItems by its orderId
     *
     * @param Integer OrderId
     * @return List of OrderItems
     * */
    public List<OrderItem> getAllProducts(int id){
        return orderItemRepository.getAllItems(id);
    }

    public void saveProducts(Product product, Order order, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setQuantity(quantity);
        orderItemRepository.save(orderItem);
    }
}
