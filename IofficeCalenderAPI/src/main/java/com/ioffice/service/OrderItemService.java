package com.ioffice.service;

import com.ioffice.model.Order;
import com.ioffice.model.OrderItem;
import com.ioffice.model.Product;
import com.ioffice.repository.OrderItemRepository;
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
