package com.bookmycon.service;

import com.bookmycon.dto.StockDTO;
import com.bookmycon.model.Product;
import com.bookmycon.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
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
            when(productRepository.findAll()).thenReturn(productList);

//        when(productRepository.findById(anyInt())).thenReturn(product);
    }

    private void setData() {
        product = Optional.of(new Product());
        products= new Product();
    }

    @Test
    public void testUpdateProduct() {

        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Test Product");
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        productService.updateProduct(product);
        verify(productRepository, times(1)).save(product);
        assertEquals("Test Product", product.getProductName());
    }

    @Test
    public void testDeleteProduct() {
        int productId = 123;
        doNothing().when(productRepository).deleteById(productId);

        String result = productService.deleteProduct(productId);

        assertEquals("Product is deleted..", result);
        verify(productRepository, times(1)).deleteById(productId);
    }
    @Test
    public void testAllProducts() {
        List<Product> actual=productService.getAllProducts();
        assertEquals(productList,actual);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductPrice(10);

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.addProduct(product);

        assertEquals("Test Product", result.getProductName());
        assertEquals(10.0, result.getProductPrice(), 0.0);
        verify(productRepository, times(1)).save(product);
    }


    @Test
    public void testAddStock() {
        int productId = 123;
        int stockValue = 50;
        Product product = new Product();
        product.setProductId(productId);
        product.setProductAvailableQTY(stockValue);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        StockDTO stockDTO = new StockDTO();
        stockDTO.setProductId(productId);
        stockDTO.setStockValue(stockValue);

        String result = productService.addStock(stockDTO);

        assertEquals("Stock is Updated For product : " + product, result);
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(product);
    }

}
