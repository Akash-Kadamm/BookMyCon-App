package com.ioffice.service;

import com.ioffice.model.Booking;
import com.ioffice.model.Product;
import com.ioffice.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;

    Optional<Product> product;

    List<Product> productList = new ArrayList<>();
    Product products;
    @Before
    public  void setUp(){
        setData();
        setMocks();
    }

    private void setMocks() {
        when(productRepository.findById(anyInt())).thenReturn(product);
    }

    private void setData() {
        product = Optional.of(new Product(1,"tea",5,5,12));
        products= new Product(1, "tea", 5,5, 12);
    }


    @Test
    public void testUpdateProduct() {
        productService.updateProduct( products);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void testAllProducts() {
        List<Product> actual=productService.getAllProducts();
        assertEquals(productList,actual);
    }
}
