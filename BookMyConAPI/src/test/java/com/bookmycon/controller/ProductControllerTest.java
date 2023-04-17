

package com.bookmycon.controller;


import com.bookmycon.dto.StockDTO;
import com.bookmycon.model.Product;
import com.bookmycon.repository.ProductRepository;
import com.bookmycon.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
  import java.util.ArrayList;
import java.util.List;

        import static org.junit.Assert.assertEquals;
        import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @Mock
    ProductService productService;
    @Mock
    private ProductRepository productRepository;
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

@Test
public void testAddNewProduct() {
    Product product = new Product();
    product.setProductId(123);
    product.setProductName("Test Product");
    product.setProductPrice(10);

    when(productService.addProduct(product)).thenReturn(product);

    ResponseEntity<Product> responseEntity = productController.addNewProduct(product);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(product, responseEntity.getBody());
    verify(productService, times(1)).addProduct(product);
}

    @Test
    public void testDeleteProduct() {
        int productId = 123;

        when(productService.deleteProduct(productId)).thenReturn("Product is deleted..");

        ResponseEntity<String> responseEntity = productController.deleteProduct(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product is deleted..", responseEntity.getBody());
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    public void testUpdateStockOfProduct() {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setProductId(123);
        stockDTO.setStockValue(10);

        when(productService.addStock(stockDTO)).thenReturn("Stock is Updated For product : Test Product");

        ResponseEntity<String> responseEntity = productController.updateStockOfProduct(stockDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Stock is Updated For product : Test Product", responseEntity.getBody());
        verify(productService, times(1)).addStock(stockDTO);
    }
}



