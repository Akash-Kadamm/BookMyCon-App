package com.bookmycon.controller;

import com.bookmycon.dto.StockDTO;
import com.bookmycon.model.Product;
import com.bookmycon.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all products ", description = "Returns  all products")
    @GetMapping("/getAllProduct")
   public ResponseEntity<List<Product>> getAllProducts(){
        return  new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK
        );
   }


    @Operation(summary = "Add new a product ", description = "Add new a product in database")
    @PostMapping("/addNewProduct")
   public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        return new ResponseEntity<>(
                productService.addProduct(product),
                HttpStatus.CREATED
        );
   }


    @Operation(summary = "Update a product ", description = "Update a product in database")
    @PutMapping("/updateProduct")
   public ResponseEntity<String> updateProduct(@RequestBody Product product){
       productService.updateProduct(product);
        return new ResponseEntity<>(
         "product Updated",
         HttpStatus.ACCEPTED
        );
   }

    @Operation(summary = "Delete a product by ID ", description = "Delete a product in database by Id")
   @DeleteMapping("/deleteProduct/{id}")
   public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return  new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
   }

    @Operation(summary = "Update a Stock ", description = "Update a stock in database")
   @PostMapping("/updateStock")
   public ResponseEntity<String> updateStockOfProduct(@RequestBody StockDTO stock){
     return new ResponseEntity<>(
       productService.addStock(stock),
       HttpStatus.CREATED
     );
   }


}
