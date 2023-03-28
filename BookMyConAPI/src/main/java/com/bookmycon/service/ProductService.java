package com.bookmycon.service;

import com.bookmycon.dto.StockDTO;
import com.bookmycon.model.Product;
import com.bookmycon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /*
     * Retrieve All products.
     *
     * @param
     * @return List of Products
     *
     * */
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    /*
     * Add new Product.
     *
     * @param Product object
     * @return Product object
     *
     * */
    public Product addProduct(Product product){
        return productRepository.save(product);
    }


    /*
     * Update product.
     *
     * @param Product object
     * @return Product object
     *
     * */
    public void updateProduct(Product product ){
         int productId=product.getProductId();
        Product savedProduct=productRepository.findById(productId).get();
        savedProduct.setProductName(product.getProductName());
        productRepository.save(savedProduct);
        
    }

    /*
     * Delete product.
     *
     * @param int productId
     * @return String
     *
     * */
    public String deleteProduct(int productId){
        productRepository.deleteById(productId);
        return  "Product is deleted..";
    }


    /*
    * Update Stock of product
    *
    * @param StockDTO object
    * @return String message
    * */
    public String addStock(StockDTO stockDTO){
        Product savedProduct=productRepository.findById(stockDTO.getProductId()).get();
        savedProduct.setProductAvailableQTY(stockDTO.getStockValue());
        Product updatedStockProduct=productRepository.save(savedProduct);
        return "Stock is Updated For product : "+savedProduct;
    }
}
