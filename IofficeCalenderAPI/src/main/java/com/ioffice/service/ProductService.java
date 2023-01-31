package com.ioffice.service;

import com.ioffice.model.Product;
import com.ioffice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product ){
         int productId=product.getProductId();
        Product savedProduct=productRepository.findById(productId).get();
        savedProduct.setProductQTY(product.getProductQTY());
        savedProduct.setProductName(product.getProductName());
        productRepository.save(savedProduct);
        return savedProduct;
    }

    public String deleteProduct(int productId){
        productRepository.deleteById(productId);
        return  "Product is deleted..";
    }

}
