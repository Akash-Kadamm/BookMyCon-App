package com.bookmycon.repository;

import com.bookmycon.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository
        extends JpaRepository<OrderItem,Integer> {


    @Query(value = "select * from order_items where order_id=?1",nativeQuery = true)
    public List<OrderItem> getAllItems(int id);
}
