package com.ioffice.model;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.util.List;


@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "booking_id")
    private int bookingId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderItem> orderItems;

}