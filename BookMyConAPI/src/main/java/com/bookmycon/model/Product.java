package com.bookmycon.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;
    @Transient
    private int quantity;
    @Column(name = "product_available_qty")
    private int productAvailableQTY;

    @Column(name = "product_price")
    private float productPrice;

}
