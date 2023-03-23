package com.bookmycon.controller;

import com.bookmycon.dto.StockDTO;
import com.bookmycon.model.Product;
import com.bookmycon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK
        );
    }


    @PostMapping("/addNewProduct")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        return new ResponseEntity<>(
                productService.addProduct(product),
                HttpStatus.CREATED
        );
    }



    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return new ResponseEntity<>(
                "product Updated",
                HttpStatus.ACCEPTED
        );
    }


    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }


    @PostMapping("/updateStock")
    public ResponseEntity<String> updateStockOfProduct(@RequestBody StockDTO stock) {
        return new ResponseEntity<>(
                productService.addStock(stock),
                HttpStatus.CREATED
        );
    }


}
