package com.bookmycon.controller;

import com.bookmycon.model.Product;
import com.bookmycon.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    Product product;

    @Before
    public void setUp() {
        setData();
    }

    public void setData() {
        product = new Product();
    }

    @Test
    public void testUpdateProduct() {
        productController.updateProduct(product);
        verify(productService, times(1)).updateProduct(product);
    }

    @Test
    public void testGetAllProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getAllProducts()).thenReturn(productList);
        ResponseEntity<List<Product>> actual = productController.getAllProducts();
        assertEquals(productList, actual.getBody());
    }

}