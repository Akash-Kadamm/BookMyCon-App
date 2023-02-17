package com.ioffice.dto;

import com.ioffice.model.Product;
import com.ioffice.model.User;
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
