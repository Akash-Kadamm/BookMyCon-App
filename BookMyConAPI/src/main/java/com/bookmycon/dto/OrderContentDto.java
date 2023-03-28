package com.bookmycon.dto;

import com.bookmycon.model.Product;
import com.bookmycon.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderContentDto {
    private List<Product> productList;
    private User user;
    private double total;
}
