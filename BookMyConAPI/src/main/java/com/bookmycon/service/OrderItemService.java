package com.bookmycon.service;

import com.bookmycon.model.Order;
import com.bookmycon.model.OrderItem;
import com.bookmycon.model.Product;
import com.bookmycon.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    Logger logger=Logger.getLogger(OrderItemService.class);

    /*
     * Fetch OrderItems by its orderId
     *
     * @param Integer OrderId
     * @return List of OrderItems
     * */
    public List<OrderItem> getAllProducts(int id){
        logger.info("Getting all products for order id: {}" + id);
        return orderItemRepository.getAllItems(id);
    }

    public void saveProducts(Product product, Order order, int quantity) {
        logger.info("Saving product with details: product_id={}" + product.getProductId() + " order_id={} " + order.getOrderId() +  "quantity={}" + quantity);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setQuantity(quantity);
        orderItemRepository.save(orderItem);
    }
}
